package br.fiap.bank.atm.model;

public interface Autorizavel {
    Boolean autorizar(String senha);
    Boolean isBloqueado();
}
