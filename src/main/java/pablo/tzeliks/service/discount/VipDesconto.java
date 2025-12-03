package pablo.tzeliks.service.discount;

public class VipDesconto implements Desconto {

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.85;
    }
}
