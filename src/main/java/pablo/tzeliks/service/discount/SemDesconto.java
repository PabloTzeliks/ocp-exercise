package pablo.tzeliks.service.discount;

public class SemDesconto implements Desconto {

    @Override
    public double calcular(double valorBruto) {
        return valorBruto;
    }
}