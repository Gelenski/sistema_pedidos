package pagamento;

import java.util.Date;

public class Boleto implements FormaPagamento{
    private Date dataVencimento;
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
