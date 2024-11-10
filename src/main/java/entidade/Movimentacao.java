package entidade;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="movimentacao")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_operacao")
	private Double valorOperacao;
	@Column(name = "data_transacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransacao=new Date();	
	@Column(name = "descricao", length = 150, nullable = true, unique = false) 
	private String descricao;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_transacao")
	private TransacaoTipo tipoTransacao;

	@ManyToOne
	@JoinColumn(name="id_conta")
	private Conta conta;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getValor() {
		return valorOperacao;
	}
	public void setValor(double valor) {
		this.valorOperacao = valor;
	}
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TransacaoTipo getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TransacaoTipo tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public String toString() {
		String dadosConta = "\nId: "+this.id+"\nNome do Correntista: "+"\nTipo de Transação: "+this.tipoTransacao+"\nValor da operação: R$"+this.valorOperacao+"\nData da operação: "+this.dataTransacao+"\nDescrição: "+this.descricao;
		return dadosConta;
	}
	
	public String toStringExtrato() {
		String extrato = "\nTipo de Transação: "+this.tipoTransacao+" às "+this.dataTransacao+"\nNo valor de: R$"+this.valorOperacao;
		return extrato;
	}
}
