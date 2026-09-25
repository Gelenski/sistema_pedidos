package pagamento;

public interface FormaPagamento {
        double valorFinal(double v);
        ResultadoPagamento pagar(double v);

}
