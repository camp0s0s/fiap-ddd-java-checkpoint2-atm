package br.fiap.bank.atm.model;

public class Cliente extends BaseEntity {
    private String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        this.nomeCompleto = nomeCompleto;
    }

    public String obterPrimeiroNome() {
        return nomeCompleto.trim().split(" ")[0];
    }

    public String getNomeCompleto() { return nomeCompleto; }
}