package pablo.tzeliks.model;

public enum TipoDesconto {

    CUPOM("Cupom"),
    VIP("VIP"),
    SAZONAL("Sazonal"),
    ANIVERSARIO("Aniversário");

    private String name;

    TipoDesconto(String name) {
        this.name = name;
    }

    public String getName(TipoDesconto tipo) {
        return tipo.name;
    }
}