package adicionais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescontoTest {

    @Test
    void subtraiValorFixo() {
        Desconto desconto = new Desconto(new ValorBase(100.0), 15.0);

        assertEquals(85.0, desconto.getValor(), 1e-9);
        assertEquals("Subtotal + Desconto", desconto.getDescricao());
    }
}
