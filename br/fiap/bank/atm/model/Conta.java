package br.fiap.bank.atm.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta extends BaseEntity {
    protected Cliente cliente;
    protected ContaAcesso contaAcesso;
    protected Dinheiro saldo;
    protected StatusConta status;
    protected List<Movimentacao> historico = new ArrayList<>();

    public Conta(Cliente cliente, Dinheiro saldoInicial, ContaAcesso contaAcesso) {
        super();
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldoInicial;
        this.status = StatusConta.ATIVA;
    }

    public final void realizarSaque(Dinheiro valor) throws Exception {
        if (status != StatusConta.ATIVA) throw new Exception("Conta não está ativa.");
        if (!saldo.ehMaiorOuIgual(valor)) throw new Exception("Saldo insuficiente.");
        
        this.saldo = saldo.subtrair(valor);
        registrarMovimentacao("SAQUE", valor);
        aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        this.saldo = saldo.somar(valor);
        registrarMovimentacao("DEPOSITO", valor);
    }

    protected abstract void aplicarRegraDeTaxa();

    protected void registrarMovimentacao(String tipo, Dinheiro valor) {
        this.historico.add(new Movimentacao(tipo, valor));
    }

    public Dinheiro getSaldo() { 
        return saldo; 
    }
    public List<Movimentacao> getHistorico() { 
        return historico; 
    }
    public StatusConta getStatus() { 
        return status; 
    }
    public void setStatus(StatusConta status) { 
        this.status = status; 
    }
    public ContaAcesso getContaAcesso() {
    return contaAcesso;
}
}