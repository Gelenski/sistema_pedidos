package adicionais;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmbalagemReforcadaTest {

    @Test
    void somaValorFixo() {
        EmbalagemReforcada embalagem = new EmbalagemReforcada(new ValorBase(100.0), 8.0);

        assertEquals(108.0, embalagem.getValor(), 1e-9);
        assertEquals("Subtotal + EmbalagemReforcada", embalagem.getDescricao());
    }
}
