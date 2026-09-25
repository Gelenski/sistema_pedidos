package pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private List<ItemPedido> itens;

    public Pedido(int id) {
        this.id = id;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(String nomeProduto, int quantidade, double precoUnitario) {
        itens.add(new ItemPedido(nomeProduto, quantidade, precoUnitario));
    }

    public double getSubtotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}