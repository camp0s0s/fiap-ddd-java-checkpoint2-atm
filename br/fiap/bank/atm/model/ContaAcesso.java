package br.fiap.bank.atm.model;

public class ContaAcesso {
    
    public static final Integer MAXIMO_TENTATIVAS = 3;
    private String senha;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senha) {
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;
    }

    public Boolean validarSenha(String senhaDigitada) {
        if (this.bloqueado) return false;

        if (this.senha.equals(senhaDigitada)) {
            resetarTentativas();
            return true;
        } else {
            this.tentativas++;
            if (this.tentativas >= MAXIMO_TENTATIVAS) {
                this.bloqueado = true;
            }
            return false;
        }
    }

    public Boolean isBloqueado() {
        return bloqueado;
    }

    public void resetarTentativas() {
        this.tentativas = 0;
    }

    // Getter para o número de tentativas (útil para o Controller)
    public Integer getTentativas() {
        return tentativas;
    }
}
