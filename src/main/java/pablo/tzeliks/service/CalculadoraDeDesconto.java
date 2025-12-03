package pablo.tzeliks.service;

import pablo.tzeliks.model.Pedido;
import pablo.tzeliks.service.discount.Desconto;
import pablo.tzeliks.service.discount.DescontoFactory;

public class CalculadoraDeDesconto {

    public Pedido CalcularDesconto(Pedido pedido) {

        Desconto tipoDesconto = DescontoFactory.criarDesconto(pedido.getDesconto());

        var valorLimpo = tipoDesconto.calcular(pedido.getValorBruto());

        pedido.setValorLimpo(valorLimpo);

        return pedido;
    }
}
