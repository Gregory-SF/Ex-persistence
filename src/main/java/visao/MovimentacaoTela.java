package visao;

import java.text.ParseException;

import controle.MovimentacaoController;
import entidade.Movimentacao;

public class MovimentacaoTela {
	
	public static void main(String[] args) throws ParseException {
		MovimentacaoController controle = new MovimentacaoController();
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setCpfCorrentista("032.274.815-10");
		movimentacao.setDescricao("pagar de 13,00 no dia 10/10/24");
		movimentacao.setNomeCorrentista("Laryssa");
		movimentacao.setTipoTransacao("Saque");
		movimentacao.setValor(10.);

		//Movimentacao contaReceptor = new Movimentacao();
		//contaReceptor.setCpfCorrentista("099.992.159-20");
		//contaReceptor.setNomeCorrentista("Diegogonçalves");
		
		controle.inserir(movimentacao);
		//controle.exibirExtratoMensal("123.456.789-09",2);
		//controle.exibirExtratoPeriodico("123.456.789-09", "2024-10-15","2024-10-18", 2);
		//controle.transferencia(conta, contaReceptor);
	}
	
}
