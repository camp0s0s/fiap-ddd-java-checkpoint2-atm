package br.fiap.bank.atm.model;

public interface Autorizacao {
    Boolean autorizar(String senha);
    Boolean isBloqueado();
}
