package entidade;

public enum TransacaoTipo {
	DEPOSITO ("Depósito"),
	PAGAMENTO ("Pagamento"),
	PIX ("Pix"),
	SAQUE ("Saque");
	
	private String operacao;
	private TransacaoTipo(String operacao) {
		this.operacao = operacao;
	}

}

