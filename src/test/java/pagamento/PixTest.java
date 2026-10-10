package pagamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixTest {

    @Test
    void valorFinalAplicaDesconto() {
        assertEquals(90.0, new Pix("chave@pix", 0.1).valorFinal(100.0), 1e-9);
    }

    @Test
    void valorFinalSemDescontoMantemValor() {
        assertEquals(100.0, new Pix("chave@pix", 0.0).valorFinal(100.0), 1e-9);
    }

    @Test
    void pagarRetornaAprovado() {
        ResultadoPagamento resultado = new Pix("chave@pix", 0.1).pagar(90.0);

        assertTrue(resultado.isAprovado());
        assertEquals("AUTH-PIX", resultado.getCodigo());
    }
}
