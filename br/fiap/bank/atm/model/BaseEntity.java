package br.fiap.bank.atm.model;
import java.time.LocalDate;
import java.util.UUID;

public abstract class BaseEntity {
    protected UUID id; 
    protected LocalDate dataCriacao; 

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDate.now(); 
    }
}