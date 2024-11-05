package entidade;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "tipo_transacao")
	private String tipoTransacao;
	@Column(name = "nome_Correntista")
	private String nomeCorrentista;
	@Column(name = "cpf_Correntista")
	private String cpfCorrentista;
	
	
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
	public String getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public String getNomeCorrentista() {
		return nomeCorrentista;
	}
	public void setNomeCorrentista(String nomeCorrentista) {
		this.nomeCorrentista = nomeCorrentista;
	}
	public String getCpfCorrentista() {
		return cpfCorrentista;
	}
	public void setCpfCorrentista(String cpfCorrentista) {
		this.cpfCorrentista = cpfCorrentista;
	}
	
	@Override
	public String toString() {
		String dadosConta = "\nId: "+this.id+"\nNome do Correntista: "+this.nomeCorrentista+"\nCpf do Correntista: "+this.cpfCorrentista+"\nTipo de Transação: "+this.tipoTransacao+"\nValor da operação: R$"+this.valorOperacao+"\nData da operação: "+this.dataTransacao+"\nDescrição: "+this.descricao;
		return dadosConta;
	}
	
	public String toStringExtrato() {
		String extrato = "\nTipo de Transação: "+this.tipoTransacao+" às "+this.dataTransacao+"\nNo valor de: R$"+this.valorOperacao;
		return extrato;
	}
}
