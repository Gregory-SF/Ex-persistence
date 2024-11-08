package entidade;

public enum ContaTipo {
	CORRENTE ("Corrente"),
	POUPANCA ("Poupança"),
	SALARIO ("Salário");

	private String tipoConta;
	private ContaTipo(String tipoConta) {
		this.tipoConta= tipoConta;
	}
}
