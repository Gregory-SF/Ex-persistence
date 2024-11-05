package entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_Cliente", length = 255, nullable = false)
	private String nomeCliente;
	@Column(name = "cpf_Cliente", length = 14, nullable = false, unique = true)
	private String cpfCliente;
	@Column(name = "rg_Cliente", length = 12, nullable = false, unique = true)
	private String rgCliente;
	@Column(name = "data_Criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;//=new Date();	
	@Column(name = "email", length = 255, nullable = true)
	private String email;
	@Column(name = "data_Alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao =new Date();	
	@Column(name = "telefone", length = 10, nullable = true) 
	private String telefone;
	
	@OneToMany(mappedBy = "cliente")
	private Conta conta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public String getRgCliente() {
		return rgCliente;
	}
	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Conta getConta() {
		return conta;
	}
	
	@Override
	public String toString() {
		String dadosConta = "\nId: "+this.id+"\nNome do Correntista: "+this.nomeCliente+"\nCpf do Correntista: "+this.cpfCliente+"\nRg do Correntista: "+this.rgCliente+"\nData da criação: "+this.dataCriacao+"\nEmail do Correntista: "+this.email+"\nÚltima alteração: "+this.dataAlteracao+"\nTelefone do Correntista: "+this.telefone;
		return dadosConta;
	}
	
	public boolean equals(Cliente outroCliente) {
		if (this == outroCliente) return true;
		if(outroCliente == null) return false;
		return nomeCliente.equalsIgnoreCase(outroCliente.getNomeCliente()) && cpfCliente.equalsIgnoreCase(outroCliente.getCpfCliente()) && rgCliente.equalsIgnoreCase(outroCliente.getRgCliente());
	}
	
	
	
}
