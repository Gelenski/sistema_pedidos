package pedido;

import adicionais.ComponenteValor;
import adicionais.ValorBase;
import pagamento.FormaPagamento;
import pagamento.ResultadoPagamento;

public class FechamentoPedido {

    private Pedido pedido;
    private ComponenteValor total;
    private FormaPagamento pagamento;

    public FechamentoPedido(Pedido pedido) {
        this.pedido = pedido;
        // Sem adicionais, o total é apenas o subtotal dos itens
        this.total = new ValorBase(pedido.getSubtotal());
    }

    public void definirTotal(ComponenteValor t) {
        this.total = t;
    }

    public void escolherPagamento(FormaPagamento f) {
        this.pagamento = f;
    }

    // Total com adicionais (decorators) + regra da forma de pagamento (desconto ou juros)
    public double calcularTotal() {
        double valorComAdicionais = total.getValor();
        if (pagamento == null) {
            return valorComAdicionais;
        }
        return pagamento.valorFinal(valorComAdicionais);
    }

    public ResultadoPagamento fecharPedido() {
        if (pagamento == null) {
            throw new IllegalStateException("Escolha uma forma de pagamento antes de fechar o pedido.");
        }
        return pagamento.pagar(calcularTotal());
    }

    public Pedido getPedido() {
        return pedido;
    }

    public ComponenteValor getTotal() {
        return total;
    }
}