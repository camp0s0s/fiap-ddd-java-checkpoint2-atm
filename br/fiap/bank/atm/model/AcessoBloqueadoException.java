package br.fiap.bank.atm.model;

public class AcessoBloqueadoException extends RuntimeException {
    public AcessoBloqueadoException(String mensagem) {
        super(mensagem);
    }
}
