package adicionais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValorBaseTest {

    @Test
    void retornaSubtotalEDescricao() {
        ValorBase base = new ValorBase(100.0);

        assertEquals(100.0, base.getValor(), 1e-9);
        assertEquals("Subtotal", base.getDescricao());
    }
}
