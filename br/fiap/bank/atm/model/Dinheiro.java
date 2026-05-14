package br.fiap.bank.atm.model;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dinheiro {
    private final BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getValor() { return valor; }

    public Dinheiro somar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.getValor()));
    }

    public Dinheiro subtrair(Dinheiro outro) {
        return new Dinheiro(this.valor.subtract(outro.getValor()));
    }

    public Boolean ehMaiorOuIgual(Dinheiro outro) {
        return this.valor.compareTo(outro.getValor()) >= 0;
    }
}