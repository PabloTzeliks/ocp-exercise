package pablo.tzeliks;

import pablo.tzeliks.model.Pedido;
import pablo.tzeliks.model.TipoDesconto;
import pablo.tzeliks.service.CalculadoraDeDesconto;

public class Main {
    public static void main(String[] args) {

        // Instanciando
        CalculadoraDeDesconto calculadora = new CalculadoraDeDesconto();

        // Ex 1 - Pedido completo, com Desconto de Aniversário
        Pedido pedido = new Pedido();
        pedido.setId("001");
        pedido.setValorBruto(1000.0);
        pedido.setDesconto(TipoDesconto.ANIVERSARIO);
        pedido.setQuantidadeItens(1);
        pedido.setPago(true);
        pedido.setEmailCliente("cliente@gmail.com");

        System.out.println(pedido);

        calculadora.CalcularDesconto(pedido);

        System.out.println(pedido);

        System.out.println();

        // Ex 2 - Pedido completo, sem Desconto
        Pedido pedido2 = new Pedido();
        pedido2.setId("002");
        pedido2.setValorBruto(1000.0);
        pedido2.setQuantidadeItens(1);
        pedido2.setPago(false);
        pedido2.setEmailCliente("cliente@gmail.com");

        System.out.println(pedido2);

        calculadora.CalcularDesconto(pedido2);

        System.out.println(pedido2);
    }
}