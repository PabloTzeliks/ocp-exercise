package pablo.tzeliks.service.discount;

import pablo.tzeliks.model.TipoDesconto;

public class VipDesconto implements Desconto {

    private TipoDesconto tipo;

    public VipDesconto() {
        this.tipo = TipoDesconto.VIP;
    }

    @Override
    public double calcular(double valorBruto) {
        return 0;
    }
}
