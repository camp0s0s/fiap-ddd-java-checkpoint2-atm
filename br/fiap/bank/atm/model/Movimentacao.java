package br.fiap.bank.atm.model;

import java.time.LocalDateTime;

public class Movimentacao {
    private String tipo;
    private Dinheiro valor;
    private LocalDateTime dataHora;

    public Movimentacao(String tipo, Dinheiro valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public String getTipo() { return tipo; }
    public Dinheiro getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
}