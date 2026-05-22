package br.fiap.bank.atm.model;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}
