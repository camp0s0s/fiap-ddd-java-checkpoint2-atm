package br.fiap.bank.atm.application;

import br.fiap.bank.atm.model.Conta;
import br.fiap.bank.atm.model.StatusConta;

public class AutorizacaoService {
    private Conta conta;

    // Este é o construtor que o seu Main está tentando chamar!
    public AutorizacaoService(Conta conta) {
        this.conta = conta;
    }

    public boolean validarSenha(String senhaDigitada) {
        // Usa a ContaAcesso que está dentro da conta para validar
        boolean valido = conta.getContaAcesso().validarSenha(senhaDigitada);
        
        // Regra de Negócio: Se a conta foi bloqueada na tentativa, atualiza o status
        if (conta.getContaAcesso().isBloqueado()) {
            conta.setStatus(StatusConta.BLOQUEADA);
        }
        
        return valido;
    }

    public boolean isBloqueado() {
        return conta.getContaAcesso().isBloqueado();
    }

    public int getTentativasRestantes() {
        // Pega o máximo (3) e subtrai as tentativas atuais do objeto de acesso
        return 3 - conta.getContaAcesso().getTentativas();
    }
}