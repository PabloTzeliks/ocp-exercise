package pablo.tzeliks.model;

import lombok.Data;

@Data
public class Pedido {

    private String id;
    private double valorBruto;
    private int quantidadeItens;
    private boolean isPago;
    private String emailCliente;
    private TipoDesconto tipoDesconto;
}