package pedido;

import adicionais.ComponenteValor;
import adicionais.Desconto;
import adicionais.EntregaExpressa;
import adicionais.Seguro;
import adicionais.ValorBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pagamento.Cartao;
import pagamento.FormaPagamento;
import pagamento.Pix;
import pagamento.ResultadoPagamento;

import static org.junit.jupiter.api.Assertions.*;

class FechamentoPedidoTest {

    private Pedido pedido;
    private FechamentoPedido fechamento;

    @BeforeEach
    void setUp() {
        pedido = new Pedido(1);
        pedido.adicionarItem("Caderno", 2, 50.0); // subtotal 100
        fechamento = new FechamentoPedido(pedido);
    }

    @Test
    void semAdicionaisNemPagamentoTotalEhOSubtotal() {
        assertSame(pedido, fechamento.getPedido());
        assertEquals(100.0, fechamento.getTotal().getValor(), 1e-9);
        assertEquals(100.0, fechamento.calcularTotal(), 1e-9);
    }

    @Test
    void definirTotalAplicaAdicionais() {
        ComponenteValor total = new Desconto(new EntregaExpressa(new ValorBase(pedido.getSubtotal()), 20.0), 10.0);
        fechamento.definirTotal(total);

        assertSame(total, fechamento.getTotal());
        assertEquals(110.0, fechamento.calcularTotal(), 1e-9);
    }

    @Test
    void pagamentoComDescontoReduzTotal() {
        fechamento.escolherPagamento(new Pix("chave@pix", 0.1));

        assertEquals(90.0, fechamento.calcularTotal(), 1e-9);
    }

    @Test
    void pagamentoComJurosAumentaTotal() {
        fechamento.escolherPagamento(new Cartao("1234567812345678", 2, 0.1));

        assertEquals(121.0, fechamento.calcularTotal(), 1e-9);
    }

    @Test
    void pagamentoEhAplicadoDepoisDosAdicionais() {
        fechamento.definirTotal(new Seguro(new ValorBase(pedido.getSubtotal()), 0.1)); // 110
        fechamento.escolherPagamento(new Pix("chave@pix", 0.1));                       // 110 - 11

        assertEquals(99.0, fechamento.calcularTotal(), 1e-9);
    }

    @Test
    void fecharPedidoSemPagamentoLancaExcecao() {
        assertThrows(IllegalStateException.class, () -> fechamento.fecharPedido());
    }

    @Test
    void fecharPedidoPagaOTotalCalculado() {
        double[] valorPago = new double[1];
        FormaPagamento pagamento = new FormaPagamento() {
            public double valorFinal(double v) {
                return v * 2;
            }

            public ResultadoPagamento pagar(double v) {
                valorPago[0] = v;
                return new ResultadoPagamento(true, "OK");
            }
        };
        fechamento.escolherPagamento(pagamento);

        ResultadoPagamento resultado = fechamento.fecharPedido();

        assertTrue(resultado.isAprovado());
        assertEquals("OK", resultado.getCodigo());
        assertEquals(200.0, valorPago[0], 1e-9);
    }
}
