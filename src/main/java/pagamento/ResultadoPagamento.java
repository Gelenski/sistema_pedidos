package pagamento;

public class ResultadoPagamento{
    private boolean aprovado;
    private String codigo;
    
    public ResultadoPagamento(boolean aprovado, String codigo) {
        this.aprovado = aprovado;
        this.codigo = codigo;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public String getCodigo() {
        return codigo;
    }
}
