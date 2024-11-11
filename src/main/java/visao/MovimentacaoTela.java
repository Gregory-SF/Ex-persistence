package visao;

import java.text.ParseException;

import controle.MovimentacaoController;
import entidade.Conta;
import entidade.Movimentacao;
import entidade.TransacaoTipo;

public class MovimentacaoTela {
	
	public static void main(String[] args) throws ParseException {
		
		MovimentacaoController controle = new MovimentacaoController();
		
		Movimentacao movimentacao = new Movimentacao();
		Conta conta = new Conta();
		
		movimentacao.setTipoTransacao(TransacaoTipo.SAQUE);
		movimentacao.setValor(100.);
		
		conta.setId(5L);
		movimentacao.setConta(conta);

		//Movimentacao contaReceptor = new Movimentacao();
		//contaReceptor.setCpfCorrentista("099.992.159-20");
		//contaReceptor.setNomeCorrentista("Diegogonçalves");
		
		controle.inserir(movimentacao);
		//controle.exibirExtratoMensal("123.456.789-09",2);
		//controle.exibirExtratoPeriodico("123.456.789-09", "2024-10-15","2024-10-18", 2);
		//controle.transferencia(conta, contaReceptor);
	}
	
}
