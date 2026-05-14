package br.fiap.bank.atm.application;
import java.math.BigDecimal;

import br.fiap.bank.atm.model.*;

public class ContaFactory {
    private static ContaFactory instance; 

    private ContaFactory() {} 

    public static ContaFactory getInstance() { 
        if (instance == null) instance = new ContaFactory();
        return instance;
    }

    public Conta criarContaCorrente(Cliente cliente, ContaAcesso acesso) {
    return new ContaCorrente(cliente, new Dinheiro(BigDecimal.ZERO), acesso);
}
}