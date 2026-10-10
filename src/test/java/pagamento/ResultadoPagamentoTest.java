package pagamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoPagamentoTest {

    @Test
    void guardaAprovacaoECodigo() {
        ResultadoPagamento aprovado = new ResultadoPagamento(true, "ABC");
        ResultadoPagamento recusado = new ResultadoPagamento(false, "XYZ");

        assertTrue(aprovado.isAprovado());
        assertEquals("ABC", aprovado.getCodigo());
        assertFalse(recusado.isAprovado());
        assertEquals("XYZ", recusado.getCodigo());
    }
}
