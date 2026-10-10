import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream outOriginal = System.out;
    private final Locale localeOriginal = Locale.getDefault();
    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US); // printf com ponto decimal
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void tearDown() {
        System.setOut(outOriginal);
        Locale.setDefault(localeOriginal);
    }

    // Executa o Main simulando o usuário digitando cada linha
    private String executar(String... linhas) {
        Main.sc = new Scanner(String.join("\n", linhas) + "\n");
        Main.main(new String[0]);
        return saida.toString();
    }

    @Test
    void pedidoComPixEEmbalagemEhAprovado() {
        String out = executar(
                "Caneta", "2", "10", "",  // item: 2 x 10 = 20
                "1", "5", "0",            // embalagem +5 = 25
                "1", "chave@pix",         // pix 5% => 23.75
                "s");

        assertTrue(out.contains("Subtotal: R$ 20.00"));
        assertTrue(out.contains("Subtotal + EmbalagemReforcada: R$ 25.00"));
        assertTrue(out.contains("Total a pagar: R$ 23.75"));
        assertTrue(out.contains("Pagamento APROVADO"));
        assertTrue(out.contains("Código: AUTH-PIX"));
    }

    @Test
    void pedidoComCartaoAplicaJurosPorParcela() {
        String out = executar(
                "TV", "1", "100", "",
                "0",
                "2", "1234567812345678", "2", // 100 * 1.0199^2 = 104.02
                "s");

        assertTrue(out.contains("Total a pagar: R$ 104.02"));
        assertTrue(out.contains("Código: AUTH-CARTAO"));
    }

    @Test
    void pedidoComBoletoSeguroEDesconto() {
        String out = executar(
                "Livro", "1", "100", "",
                "3", "10",  // seguro 10% => 110
                "4", "10",  // desconto 10 => 100
                "0",
                "3",        // boleto 3% => 97
                "s");

        assertTrue(out.contains("Subtotal + Seguro + Desconto: R$ 100.00"));
        assertTrue(out.contains("Total a pagar: R$ 97.00"));
        assertTrue(out.contains("Código: 34191.09008 61718.123456 9 12345000000000"));
    }

    @Test
    void entregaExpressaEhSomadaAoTotal() {
        String out = executar(
                "Caneta", "1", "10", "",
                "2", "15", "0",   // entrega +15 => 25
                "1", "chave",
                "n");

        assertTrue(out.contains("Subtotal + EntregaExpressa: R$ 25.00"));
    }

    @Test
    void pedidoNaoConfirmadoEhCancelado() {
        String out = executar(
                "Caneta", "1", "10", "",
                "0",
                "1", "chave",
                "n");

        assertTrue(out.contains("Pedido cancelado."));
        assertFalse(out.contains("Código:"));
    }

    @Test
    void entradasInvalidasSaoRejeitadasEPedidasDeNovo() {
        String out = executar(
                "",                          // sem itens ainda
                "Caneta", "abc", "2", "2,5", // quantidade inválida, preço com vírgula => 5.00
                "",
                "9", "0",                    // adicional inválido
                "9", "1", "chave",           // pagamento inválido
                "n");

        assertTrue(out.contains("Adicione ao menos um item."));
        assertTrue(out.contains("Número inválido, tente de novo."));
        assertTrue(out.contains("Subtotal: R$ 5.00"));
        assertEquals(2, out.split("Opção inválida.", -1).length - 1);
    }
}
