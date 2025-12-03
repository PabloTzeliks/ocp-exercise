package pablo.tzeliks.service.discount;

public class CupomDesconto implements Desconto {

    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.9;
    }
}
