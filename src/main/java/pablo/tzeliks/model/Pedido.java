package pablo.tzeliks.model;

import lombok.Data;

@Data
public class Pedido {

    private String id;
    private double valorBruto;
    private double valorLimpo;
    private TipoDesconto desconto;
    private int quantidadeItens;
    private boolean isPago;
    private String emailCliente;
}