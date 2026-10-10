package pagamento;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BoletoTest {

    @Test
    void valorFinalAplicaDesconto() {
        assertEquals(95.0, new Boleto(new Date(), 0.05).valorFinal(100.0), 1e-9);
    }

    @Test
    void valorFinalSemDescontoMantemValor() {
        assertEquals(100.0, new Boleto(new Date(), 0.0).valorFinal(100.0), 1e-9);
    }

    @Test
    void pagarRetornaAprovadoComLinhaDigitavel() {
        ResultadoPagamento resultado = new Boleto(new Date(), 0.05).pagar(95.0);

        assertTrue(resultado.isAprovado());
        assertEquals("34191.09008 61718.123456 9 12345000000000", resultado.getCodigo());
    }
}
