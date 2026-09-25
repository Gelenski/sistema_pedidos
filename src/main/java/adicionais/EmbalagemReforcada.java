package adicionais;

public class EmbalagemReforcada extends AjusteDecorator {

    private double valor;

    public EmbalagemReforcada(ComponenteValor componente, double valor) {
        super(componente);
        this.valor = valor;
    }

    @Override
    public double getValor() {
        return componente.getValor() + valor;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + EmbalagemReforcada";
    }
}
