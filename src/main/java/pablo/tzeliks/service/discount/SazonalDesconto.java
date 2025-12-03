package pablo.tzeliks.service.discount;

public class SazonalDesconto implements Desconto {

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.95;
    }
}
