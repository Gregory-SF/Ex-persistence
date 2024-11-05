package entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao=new Date();	
	@Column(name = "numero_conta", nullable = false)//, unique = false) 
	private String numeroConta;
	@Column(name = "tipo_conta")
	private String tipoConta;
	@Column(name = "conta_ativa")
	private boolean contaAtiva;
	
	@ManyToOne
	@JoinColumn(name="cliente_id") //opcional, mas coloquei pra não esquecer o que faz
	private Cliente cliente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public boolean isContaAtiva() {
		return contaAtiva;
	}
	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	@Override
	public String toString() {
		String dadosCliente = "\nId: "+this.id+"\nNúmero da Conta: "+this.numeroConta+"\nTipo da Conta: "+this.tipoConta+"\nConta está ativa: "+this.contaAtiva+"\nData da criação: "+this.dataCriacao+"\nId do cliente que se refere: "+this.cliente.getId();
		return dadosCliente;
	}
	
}
