package pablo.tzeliks.service.discount;

import pablo.tzeliks.model.TipoDesconto;

public class DescontoFactory {

    public static Desconto criarDesconto(TipoDesconto tipoDesconto) {

        if (tipoDesconto == null) {

            return new SemDesconto();
        }

        return switch (tipoDesconto) {

            case CUPOM -> new CupomDesconto();
            case VIP -> new VipDesconto();
            case SAZONAL -> new SazonalDesconto();
            case ANIVERSARIO -> new AniversarioDesconto();

            default -> throw new IllegalArgumentException("Desconto de Tipo: " + tipoDesconto + " é inválido.");
        };
    }
}
