import adicionais.*;
import pagamento.*;
import pedido.FechamentoPedido;
import pedido.ItemPedido;
import pedido.Pedido;

import java.util.Date;
import java.util.Scanner;

public class Main {

    static final double DESCONTO_PIX = 0.05;
    static final double DESCONTO_BOLETO = 0.03;
    static final double JUROS_CARTAO_MES = 0.0199;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PEDIDOS ===");

        Pedido pedido = new Pedido(1);
        System.out.println("\nAdicione os itens (nome vazio para terminar):");
        while (true) {
            String nome = lerTexto("Produto: ");
            if (nome.isEmpty()) {
                if (pedido.getItens().isEmpty()) {
                    System.out.println("Adicione ao menos um item.");
                    continue;
                }
                break;
            }
            int qtd = (int) lerNumero("Quantidade: ");
            double preco = lerNumero("Preço unitário: R$ ");
            pedido.adicionarItem(nome, qtd, preco);
        }

        FechamentoPedido fechamento = new FechamentoPedido(pedido);
        System.out.printf("%nSubtotal: R$ %.2f%n", pedido.getSubtotal());

        ComponenteValor total = new ValorBase(pedido.getSubtotal());
        int op;
        do {
            System.out.println("\nAdicionais:");
            System.out.println("1 - Embalagem reforçada");
            System.out.println("2 - Entrega expressa");
            System.out.println("3 - Seguro (%)");
            System.out.println("4 - Desconto (R$)");
            System.out.println("0 - Continuar");
            op = (int) lerNumero("Opção: ");
            switch (op) {
                case 1 -> total = new EmbalagemReforcada(total, lerNumero("Valor: R$ "));
                case 2 -> total = new EntregaExpressa(total, lerNumero("Valor: R$ "));
                case 3 -> total = new Seguro(total, lerNumero("Percentual: ") / 100);
                case 4 -> total = new Desconto(total, lerNumero("Valor: R$ "));
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
            System.out.printf("Atual: %s = R$ %.2f%n", total.getDescricao(), total.getValor());
        } while (op != 0);
        fechamento.definirTotal(total);

        FormaPagamento forma = null;
        while (forma == null) {
            System.out.println("\nForma de pagamento:");
            System.out.printf("1 - Pix (%.0f%% de desconto)%n", DESCONTO_PIX * 100);
            System.out.printf("2 - Cartão (%.2f%% de juros a.m.)%n", JUROS_CARTAO_MES * 100);
            System.out.printf("3 - Boleto (%.0f%% de desconto)%n", DESCONTO_BOLETO * 100);
            switch ((int) lerNumero("Opção: ")) {
                case 1 -> forma = new Pix(lerTexto("Chave Pix: "), DESCONTO_PIX);
                case 2 -> {
                    String numero = lerTexto("Número do cartão: ");
                    int parcelas = (int) lerNumero("Parcelas: ");
                    forma = new Cartao(numero, parcelas, JUROS_CARTAO_MES);
                }
                case 3 -> {
                    Date vencimento = new Date(System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000);
                    forma = new Boleto(vencimento, DESCONTO_BOLETO);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        fechamento.escolherPagamento(forma);

        System.out.println("\n=== RESUMO DO PEDIDO #" + pedido.getId() + " ===");
        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("%dx %s  R$ %.2f%n", item.getQuantidade(), item.getNomeProduto(), item.getSubtotal());
        }
        System.out.printf("%s: R$ %.2f%n", total.getDescricao(), total.getValor());
        System.out.printf("Total a pagar: R$ %.2f%n", fechamento.calcularTotal());

        if (!lerTexto("\nConfirmar pedido? (s/n): ").equalsIgnoreCase("s")) {
            System.out.println("Pedido cancelado.");
            return;
        }

        ResultadoPagamento resultado = fechamento.fecharPedido();
        System.out.println(resultado.isAprovado() ? "Pagamento APROVADO" : "Pagamento RECUSADO");
        System.out.println("Código: " + resultado.getCodigo());
    }

    static String lerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static double lerNumero(String msg) {
        while (true) {
            try {
                return Double.parseDouble(lerTexto(msg).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, tente de novo.");
            }
        }
    }
}
