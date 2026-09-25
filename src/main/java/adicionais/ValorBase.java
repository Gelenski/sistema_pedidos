package adicionais;

public class ValorBase implements ComponenteValor {

    private double subtotal;

    public ValorBase(double subtotal) {
        this.subtotal = subtotal;
    }


    @Override
    public double getValor() {
        return subtotal;
    }

    @Override
    public String getDescricao() {
        return "Subtotal";
    }
}
