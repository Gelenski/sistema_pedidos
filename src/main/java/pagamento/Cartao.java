package pagamento;

public class Cartao implements FormaPagamento{
    private String numero;
    private int quantParcelas;
    private double percentualJurosMes;

    public Cartao(String numero, int quantParcelas, double percentualJurosMes) {
        this.numero = numero;
        this.quantParcelas = quantParcelas;
        this.percentualJurosMes = percentualJurosMes;
    }

    @Override
    public double valorFinal(double v) {

        return v * Math.pow(1 + percentualJurosMes, quantParcelas);
    }
    @Override
    public ResultadoPagamento pagar(double v) {
        System.out.println("Processando cartão final " + numero.substring(numero.length() - 4));
        return new ResultadoPagamento(true, "AUTH-CARTAO");
    }
}
