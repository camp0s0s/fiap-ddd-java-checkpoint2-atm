package br.fiap.bank.atm.model;

public class ContaAcesso implements Autorizavel {
    
    public static final Integer MAXIMO_TENTATIVAS = 3;
    private String senha;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senha) {
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;
    }

    @Override
    public Boolean autorizar(String senhaDigitada) {
        if (this.bloqueado) {
            throw new AcessoBloqueadoException("Acesso indisponível. Esta conta está bloqueada.");
        }

        if (this.senha.equals(senhaDigitada)) {
            this.tentativas = 0;
            return true;
        } else {
            this.tentativas++;
            if (this.tentativas >= MAXIMO_TENTATIVAS) {
                this.bloqueado = true;
                throw new AcessoBloqueadoException("Senha incorreta. Limite de tentativas excedido! Conta bloqueada.");
            }
            return false;
        }
    }

    @Override
    public Boolean isBloqueado() {
        return this.bloqueado;
    }

    public Integer getTentativas() {
        return tentativas;
    }
}
