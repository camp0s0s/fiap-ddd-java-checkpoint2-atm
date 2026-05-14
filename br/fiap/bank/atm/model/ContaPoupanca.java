package br.fiap.bank.atm.model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente c, Dinheiro s, ContaAcesso a) {
        super(c, s, a); 
    }


    @Override
    protected void aplicarRegraDeTaxa() { 
        
    }
}