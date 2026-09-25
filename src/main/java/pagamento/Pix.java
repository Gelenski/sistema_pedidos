package pagamento;

public class Pix implements FormaPagamento{
    private String chave;
    private double descontoPerc;

    @Override 
    public double valorFinal(double v) {

        return v;
    }

    @Override
    public ResultadoPagamento pagar(double v) {
        
        return v;
    }
}
