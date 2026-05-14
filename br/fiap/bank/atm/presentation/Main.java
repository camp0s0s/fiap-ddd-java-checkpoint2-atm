package br.fiap.bank.atm.presentation;

import br.fiap.bank.atm.application.AutorizacaoService;
import br.fiap.bank.atm.application.ContaFactory;
import br.fiap.bank.atm.application.ContaService;
import br.fiap.bank.atm.model.Cliente;
import br.fiap.bank.atm.model.Conta;
import br.fiap.bank.atm.model.ContaAcesso;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Davi Jonas da Silva");

        ContaAcesso acesso = new ContaAcesso("Senha123!");

        Conta conta = ContaFactory.getInstance().criarContaCorrente(cliente, acesso);
        
        ContaService contaService = new ContaService(conta);
        AutorizacaoService autorizacaoService = new AutorizacaoService(conta);

        TerminalBancarioController controller = new TerminalBancarioController(contaService, autorizacaoService);
 
        controller.exibirMenuPrincipal();
    }
}