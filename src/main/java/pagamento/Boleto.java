package pagamento;

import java.util.Date;

public class Boleto implements FormaPagamento{
    private Date dataVencimento;
    private double descontoPerc;

    public Boleto(Date dataVencimento, double descontoPerc) {
        this.dataVencimento = dataVencimento;
        this.descontoPerc = descontoPerc;
    }

    @Override 
    public double valorFinal(double v) {
        return v - (v * descontoPerc);
    }

    @Override
    public ResultadoPagamento pagar(double v) {
    
        System.out.println("Gerando boleto R$ " + v + "com vencimento para: " +dataVencimento);
        return new ResultadoPagamento(true, "34191.09008 61718.123456 9 12345000000000");
    }
    
}
