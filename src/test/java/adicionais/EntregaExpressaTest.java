package adicionais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntregaExpressaTest {

    @Test
    void somaValorFixo() {
        EntregaExpressa entrega = new EntregaExpressa(new ValorBase(100.0), 20.0);

        assertEquals(120.0, entrega.getValor(), 1e-9);
        assertEquals("Subtotal + EntregaExpressa", entrega.getDescricao());
    }
}
