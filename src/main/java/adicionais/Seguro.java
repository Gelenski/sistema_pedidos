package adicionais;

public class Seguro extends AjusteDecorator {

    private double percentual;

    public Seguro(ComponenteValor componente, double percentual) {
        super(componente);
        this.percentual = percentual;
    }

    @Override
    public double getValor() {
        return componente.getValor() + percentual * componente.getValor();
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Seguro";
    }
}
