package br.fiap.bank.atm.presentation;
import br.fiap.bank.atm.application.AutorizacaoService;
import br.fiap.bank.atm.application.ContaService;
import br.fiap.bank.atm.model.AcessoBloqueadoException;
import br.fiap.bank.atm.model.Dinheiro;
import br.fiap.bank.atm.model.Movimentacao;
import br.fiap.bank.atm.model.SaldoInsuficienteException;
import br.fiap.bank.atm.model.ValorInvalidoException;

import java.math.BigDecimal;
import java.util.Scanner;

public class TerminalBancarioController {

    private Scanner scanner = new Scanner(System.in);
    private ContaService contaService;
    private AutorizacaoService autorizacaoService;

    public TerminalBancarioController(ContaService contaService, AutorizacaoService autorizacaoService) {
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
    }

    public void exibirMenuPrincipal() {
        try {
        if (!realizarLogin()) {
            return;
        }
    } catch (AcessoBloqueadoException e) {
        System.out.println("\n[!] " + e.getMessage());
        System.out.println("Sessão encerrada por segurança.");
        return;
    }

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n--- FIAP BANK ATM (BETA) ---");
            System.out.println("[1] Consultar Saldo");
            System.out.println("[2] Fazer Depósito");
            System.out.println("[3] Fazer Saque");
            System.out.println("[4] Ver Extrato (Histórico)");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> exibirSaldo();
                    case 2 -> realizarDeposito();
                    case 3 -> realizarSaque();
                    case 4 -> exibirMovimentacoes();
                    case 5 -> System.out.println("Sessão finalizada com sucesso.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (SaldoInsuficienteException | ValorInvalidoException | AcessoBloqueadoException e) {
                System.out.println("/\nSTATUS DA TRANSAÇÃO: ERRO");
                System.out.println("[!] " + e.getMessage());
                System.out.println("Retornando ao Menu Principal...");
            } catch (NumberFormatException e) {
                System.out.println("\n[!] Erro de digitação: Por favor, digite apenas números.");
            } catch (Exception e) {
                System.out.println("\n[!] Ocorreu um erro inesperado no sistema: " + e.getMessage());
            }
        }
    }

    private boolean realizarLogin() {
        while (!autorizacaoService.isBloqueado()) {
            System.out.print("Digite sua senha de acesso: ");
            String senha = scanner.nextLine();

            if (autorizacaoService.validarSenha(senha)) {
                return true;
            } else {
                System.out.println("Senha incorreta! Tentativas restantes: " + autorizacaoService.getTentativasRestantes());
            }
        }
        return false;
    }

    public void exibirSaldo() {
        BigDecimal valor = contaService.getConta().getSaldo().getValor();
        System.out.printf("Saldo atual: R$ %.2f%n", valor);
    }

    public void realizarDeposito() {
        System.out.print("Digite o valor do depósito: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());
        contaService.realizarDeposito(new Dinheiro(valor));
        System.out.println("Depósito realizado com sucesso!");
    }

    public void realizarSaque() {
        System.out.print("Digite o valor do saque: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());
        
        // Dispara o fluxo que pode lançar SaldoInsuficienteException ou ValorInvalidoException [cite: 65, 66, 75]
        contaService.realizarSaque(new Dinheiro(valor));
        System.out.println("Saque processado com sucesso! Retire as cédulas.");
    }

    public void exibirMovimentacoes() {
        System.out.println("\n--- EXTRATO DETALHADO ---");
        for (Movimentacao m : contaService.getConta().getHistorico()) {
            System.out.printf("%s | %-12s | R$ %.2f%n", 
                m.getDataHora(), 
                m.getTipo(), 
                m.getValor().getValor());
        }
    }
}