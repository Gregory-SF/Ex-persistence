package visao;

import java.text.ParseException;

import controle.ContaControler;
import entidade.Conta;

public class ContaTela {
	
	public static void main(String[] args) throws ParseException {
		ContaControler controle = new ContaControler();
		Conta conta = new Conta();
		conta.setCpfCorrentista("032.274.815-10");
		conta.setDescricao("pagar de 13,00 no dia 10/10/24");
		conta.setNomeCorrentista("Laryssa");
		conta.setTipoTransacao("Saque");
		conta.setValor(10.);

		//Conta contaReceptor = new Conta();
		//contaReceptor.setCpfCorrentista("099.992.159-20");
		//contaReceptor.setNomeCorrentista("Diegogonçalves");
		
		controle.inserir(conta);
		//controle.exibirExtratoMensal("123.456.789-09",2);
		//controle.exibirExtratoPeriodico("123.456.789-09", "2024-10-15","2024-10-18", 2);
		//controle.transferencia(conta, contaReceptor);
	}
	
}
