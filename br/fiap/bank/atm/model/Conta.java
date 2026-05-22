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

    public final void realizarSaque(Dinheiro valor) {
    if (status == StatusConta.BLOQUEADA || contaAcesso.isBloqueado()) {
        throw new AcessoBloqueadoException("Não é possível sacar. A conta está bloqueada.");
    }
    if (valor.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0) {
        throw new ValorInvalidoException("O valor do saque deve ser maior que zero.");
    }
    if (!saldo.ehMaiorOuIgual(valor)) {
        throw new SaldoInsuficienteException("Saldo Insuficiente! Não foi possível realizar o saque do valor selecionado.");
    }

    this.saldo = saldo.subtrair(valor);
    registrarMovimentacao("SAQUE", valor);
    aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        if (valor.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0) {
        throw new ValorInvalidoException("O valor do depósito deve ser positivo.");
    }
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