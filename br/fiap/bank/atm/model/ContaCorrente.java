package br.fiap.bank.atm.model;

public class ContaCorrente extends Conta {
    
    public ContaCorrente(Cliente cliente, Dinheiro saldo, ContaAcesso acesso) {
        super(cliente, saldo, acesso); 
    }

    @Override
    protected void aplicarRegraDeTaxa() {
    }
}