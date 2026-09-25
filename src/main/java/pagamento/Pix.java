package pagamento;

public class Pix implements FormaPagamento{
    private String chave;
    private double descontoPerc;

    public Pix(String chave, double descontoPerc) {
        this.chave = chave;
        this.descontoPerc = descontoPerc;
    }

    @Override 
    public double valorFinal(double v) {

        return v - (v * descontoPerc);
    }

    @Override
    public ResultadoPagamento pagar(double v) {
        
        System.out.println("Processando PIX para chave " + chave);
        return new ResultadoPagamento(true, "AUTH-PIX");
    }
}
