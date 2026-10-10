package adicionais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeguroTest {

    @Test
    void somaPercentualDoValor() {
        Seguro seguro = new Seguro(new ValorBase(100.0), 0.05);

        assertEquals(105.0, seguro.getValor(), 1e-9);
        assertEquals("Subtotal + Seguro", seguro.getDescricao());
    }

    @Test
    void percentualIncideSobreAdicionaisAnteriores() {
        // (100 + 20 + 8) * 1.1 = 140.8
        ComponenteValor total = new Seguro(
                new EmbalagemReforcada(new EntregaExpressa(new ValorBase(100.0), 20.0), 8.0), 0.1);

        assertEquals(140.8, total.getValor(), 1e-9);
        assertEquals("Subtotal + EntregaExpressa + EmbalagemReforcada + Seguro", total.getDescricao());
    }
}
