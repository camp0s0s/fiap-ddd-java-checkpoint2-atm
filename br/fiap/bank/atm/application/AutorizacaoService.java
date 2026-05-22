package br.fiap.bank.atm.application;

import br.fiap.bank.atm.model.Conta;
import br.fiap.bank.atm.model.StatusConta;

public class AutorizacaoService {
    private Conta conta;

    public AutorizacaoService(Conta conta) {
        this.conta = conta;
    }

    public boolean validarSenha(String senhaDigitada) {
        boolean valido = conta.getContaAcesso().autorizar(senhaDigitada);
        
        if (conta.getContaAcesso().isBloqueado()) {
            conta.setStatus(StatusConta.BLOQUEADA);
        }
        
        return valido;
    }

    public boolean isBloqueado() {
        return conta.getContaAcesso().isBloqueado();
    }

    public int getTentativasRestantes() {
        return 3 - conta.getContaAcesso().getTentativas();
    }
}