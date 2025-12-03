package pablo.tzeliks.service.discount;

import pablo.tzeliks.model.TipoDesconto;

public class CupomDesconto implements Desconto {

    private TipoDesconto tipo;

    public CupomDesconto(TipoDesconto tipo) {
        this.tipo = tipo;
    }

    @Override
    public double calcular(double valorBruto) {
        return 0;
    }
}
