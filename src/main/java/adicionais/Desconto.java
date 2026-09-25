package adicionais;

public class Desconto extends AjusteDecorator {

    private double desconto;

    public Desconto(ComponenteValor componente, double desconto) {
        super(componente);
        this.desconto = desconto;
    }

    @Override
    public double getValor() {
        return componente.getValor() - desconto;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Desconto";
    }
}
