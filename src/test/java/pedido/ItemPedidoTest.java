package pedido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

    @Test
    void construtorGuardaDadosDoItem() {
        ItemPedido item = new ItemPedido("Caneta", 3, 2.5);

        assertEquals("Caneta", item.getNomeProduto());
        assertEquals(3, item.getQuantidade());
        assertEquals(2.5, item.getPrecoUnitario());
    }

    @Test
    void subtotalEhQuantidadeVezesPreco() {
        assertEquals(7.5, new ItemPedido("Caneta", 3, 2.5).getSubtotal(), 1e-9);
    }

    @Test
    void subtotalComQuantidadeZeroEhZero() {
        assertEquals(0.0, new ItemPedido("Caneta", 0, 2.5).getSubtotal(), 1e-9);
    }
}
