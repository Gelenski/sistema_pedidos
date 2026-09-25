package adicionais;

public abstract class AjusteDecorator implements ComponenteValor {
    protected ComponenteValor componente;

    public AjusteDecorator(ComponenteValor componente) {
        this.componente = componente;
    }
}
