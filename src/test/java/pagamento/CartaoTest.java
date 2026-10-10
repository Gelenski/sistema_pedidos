package pagamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartaoTest {

    @Test
    void valorFinalAplicaJurosCompostosPorParcela() {
        // 100 * 1.1^2
        assertEquals(121.0, new Cartao("1234567812345678", 2, 0.1).valorFinal(100.0), 1e-9);
    }

    @Test
    void valorFinalSemJurosMantemValor() {
        assertEquals(100.0, new Cartao("1234567812345678", 12, 0.0).valorFinal(100.0), 1e-9);
    }

    @Test
    void valorFinalComZeroParcelasMantemValor() {
        assertEquals(100.0, new Cartao("1234567812345678", 0, 0.1).valorFinal(100.0), 1e-9);
    }

    @Test
    void pagarRetornaAprovado() {
        ResultadoPagamento resultado = new Cartao("1234567812345678", 1, 0.1).pagar(110.0);

        assertTrue(resultado.isAprovado());
        assertEquals("AUTH-CARTAO", resultado.getCodigo());
    }

    @Test
    void pagarComNumeroCurtoNaoLancaExcecao() {
        assertTrue(new Cartao("12", 1, 0.1).pagar(110.0).isAprovado());
        assertTrue(new Cartao("", 1, 0.1).pagar(110.0).isAprovado());
    }
}
