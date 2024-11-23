package visao;

import java.text.ParseException;
import java.util.Date;

import controle.MovimentacaoController;
import entidade.Conta;
import entidade.Movimentacao;
import entidade.TransacaoTipo;

public class MovimentacaoTela {
	
	public static void main(String[] args) throws ParseException {
		
		MovimentacaoController controle = new MovimentacaoController();
		
		Movimentacao movimentacao = new Movimentacao();
		Conta conta = new Conta();
		
		movimentacao.setTipoTransacao(TransacaoTipo.DEPOSITO);
		movimentacao.setValor(100.);
		movimentacao.setDataTransacao(new Date());
		
		conta.setId(2L);
		movimentacao.setConta(conta);
		
		controle.inserir(movimentacao);
		//controle.calcularContaPoupanca(movimentacao);
	}
	
}
