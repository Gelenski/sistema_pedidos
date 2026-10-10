package pedido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void novoPedidoTemIdEItensVazios() {
        Pedido pedido = new Pedido(7);

        assertEquals(7, pedido.getId());
        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.0, pedido.getSubtotal());
    }

    @Test
    void adicionarItemGuardaDadosDoItem() {
        Pedido pedido = new Pedido(1);
        pedido.adicionarItem("Caneta", 3, 2.5);

        assertEquals(1, pedido.getItens().size());
        ItemPedido item = pedido.getItens().get(0);
        assertEquals("Caneta", item.getNomeProduto());
        assertEquals(3, item.getQuantidade());
        assertEquals(2.5, item.getPrecoUnitario());
    }

    @Test
    void subtotalSomaQuantidadeVezesPrecoDeTodosOsItens() {
        Pedido pedido = new Pedido(1);
        pedido.adicionarItem("Caneta", 3, 2.5);   // 7.5
        pedido.adicionarItem("Caderno", 2, 15.0); // 30.0

        assertEquals(37.5, pedido.getSubtotal(), 1e-9);
    }

    @Test
    void itensMantemOrdemDeInsercao() {
        Pedido pedido = new Pedido(1);
        pedido.adicionarItem("A", 1, 1.0);
        pedido.adicionarItem("B", 1, 1.0);

        assertEquals("A", pedido.getItens().get(0).getNomeProduto());
        assertEquals("B", pedido.getItens().get(1).getNomeProduto());
    }
}
