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
@Table(name="conta")
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
	@Enumerated(EnumType.STRING)
	private ContaTipo tipoConta;
	@Column(name = "conta_ativa")
	private boolean contaAtiva;
	@Column(name = "data_Alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao = new Date();	
	
	@ManyToOne
	@JoinColumn(name="id_cliente") //opcional, mas coloquei pra não esquecer o que faz
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
	public ContaTipo getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(ContaTipo contaTipo) {
		this.tipoConta = contaTipo;
	}
	public boolean isContaAtiva() {
		return contaAtiva;
	}
	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		String dadosConta = "\nId: "+this.id+"\nNúmero da Conta: "+this.numeroConta+"\nTipo da Conta: "+this.tipoConta+"\nConta está ativa: "+this.contaAtiva+"\nData da criação: "+this.dataCriacao+"\nData de alteração: "+this.dataAlteracao+"\nId do cliente que se refere: "+this.cliente.getId();
		return dadosConta;
	}
	
//	public boolean equals(Conta outroConta) {
//		if (this == outroConta) return true;
//		if(outroConta == null) return false;
//		return nomeCliente.equalsIgnoreCase(outroCliente.getNomeCliente()) && cpfCliente.equalsIgnoreCase(outroCliente.getCpfCliente()) && rgCliente.equalsIgnoreCase(outroCliente.getRgCliente());
//	}
}
