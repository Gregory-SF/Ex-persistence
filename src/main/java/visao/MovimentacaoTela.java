package visao;

import java.text.ParseException;

import controle.MovimentacaoController;
import entidade.Conta;
import entidade.Movimentacao;
import entidade.TransacaoTipo;
import util.FormatarData;

public class MovimentacaoTela {
	
	public static void main(String[] args) throws ParseException {
		
		MovimentacaoController controle = new MovimentacaoController();
		
		Movimentacao movimentacao = new Movimentacao();
		Conta conta = new Conta();
		
		movimentacao.setTipoTransacao(TransacaoTipo.DEPOSITO);
		movimentacao.setValor(100.);
		movimentacao.setDataTransacao(FormatarData.formatarStringDate("2025-01-01"));
		
		conta.setId(7L);
		movimentacao.setConta(conta);
		
		//controle.inserir(movimentacao);
		//controle.exibirExtratoMensal("123.456.789-09",2);
		//controle.exibirExtratoPeriodico("123.456.789-09", "2024-10-15","2024-10-18", 2);
		//controle.calcularContaPoupanca(movimentacao);
	}
	
}
