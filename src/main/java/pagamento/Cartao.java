package pagamento;

public class Cartao implements FormaPagamento{
    private String numero;
    private int quantParcelas;
    private double percentualJurosMes;

    @Override
    public double valorFinal(double v) {

        return v;
    }
    @Override
    public ResultadoPagamento pagar(double v) {
        
        return v;
    }
}
