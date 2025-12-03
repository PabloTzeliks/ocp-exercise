package pablo.tzeliks.service.discount;

import pablo.tzeliks.model.TipoDesconto;

public class Sazonal implements Desconto {

    private TipoDesconto tipo;

    public Sazonal(TipoDesconto tipo) {
        this.tipo = tipo;
    }

    @Override
    public double calcular(double valorBruto) {
        return 0;
    }
}
