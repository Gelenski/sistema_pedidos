package adicionais;

public class EntregaExpressa extends AjusteDecorator {

    private double valor;

    public EntregaExpressa(ComponenteValor componente, double valor) {
        super(componente);
        this.valor = valor;
    }

    @Override
    public double getValor() {
        return componente.getValor() + valor;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + EntregaExpressa";
    }
}
