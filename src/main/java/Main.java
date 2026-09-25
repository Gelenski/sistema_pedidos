import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido(1);
        pedido.adicionarItem("Teclado", 1, 250.00);
        pedido.adicionarItem("Mouse", 2, 80.00);

        System.out.println("Subtotal: " + pedido.getSubtotal()); // 410.0
    }
}