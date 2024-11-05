package servico;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dao.MovimentacaoDAO;
import entidade.Movimentacao;
import util.FormatarData;
import util.ValidacaoCpf;

public class MovimentacaoService {
	MovimentacaoDAO dao = new MovimentacaoDAO();
	public enum TipoTransacao{
		DEPOSITO ("Depósito"),
		PAGAMENTO ("Pagamento"),
		PIX ("Pix"),
		SAQUE ("Saque");
		
		private String operacao;
		private TipoTransacao(String operacao) {
			this.operacao = operacao;
		}
		private String getTipoTransacao() {
			return operacao;
		}
	}

	
	public Movimentacao inserir(Movimentacao movimentacao) {
		movimentacao = validarCompleto(movimentacao);
		movimentacao.setDescricao(movimentacao.getTipoTransacao().toLowerCase()+"de R$"+movimentacao.getValor());
		return dao.inserir(movimentacao);
	}
	
	private Movimentacao validarCompleto(Movimentacao movimentacao) {
		if(!ValidacaoCpf.validarCpf(movimentacao.getCpfCorrentista())) throw new Error("CPF inválido");
		if(movimentacao.getValor()==0) throw new Error("Valor não pode ser nulo");
		limiteOperacoes(movimentacao);
		movimentacao = validarTipoTransacao(movimentacao);
		validarSaldo(movimentacao);
		return movimentacao;
	}
	
	private Movimentacao validarTipoTransacao(Movimentacao movimentacao) {
		for (TipoTransacao transacao: TipoTransacao.values()) {
			if (movimentacao.getTipoTransacao().equalsIgnoreCase(transacao.operacao)) {
				movimentacao.setTipoTransacao(transacao.getTipoTransacao());
				if(transacao.operacao.equals("Pagamento") || transacao.operacao.equals("Pix")) {
					movimentacao.setValor(movimentacao.getValor()*-1-5);
					if(transacao.operacao.equals("Pix")) validarPix(movimentacao);
				}
				else if(transacao.operacao.equals("Saque")) {
					movimentacao.setValor(movimentacao.getValor()*-1-2);
					validarSaque(movimentacao);
				}
				return movimentacao;					
			}
		}
		throw new Error ("Tipo de transação inválida");
	}
	
	private void validarPix(Movimentacao movimentacao) {
		int hora = FormatarData.formatarHora(movimentacao.getDataTransacao());
		if(hora>22 || hora<6) throw new Error("Fora do horário permitido");
		if(Math.abs(movimentacao.getValor())>300.00) throw new Error("Valor máximo do pix ultrapassado");
	}
	
	private void validarSaque(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		double valor = dao.buscarSaldoSaque(movimentacao.getCpfCorrentista(),dataFormatada);
		valor += movimentacao.getValor();
		System.out.println(valor);
		if(valor<-5000) throw new Error("Transação cancelada, limite diário de saque alcançado");	
	}
	
	private void limiteOperacoes(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		if(dao.buscarPorCpfData(movimentacao.getCpfCorrentista(),dataFormatada).size()==10) throw new Error("Limite máximo de 10 transações atingido");
	}
	
	private void validarSaldo(Movimentacao movimentacao) {
		double saldo = dao.buscarSaldo(movimentacao.getCpfCorrentista())+movimentacao.getValor();
		if(saldo<0) throw new Error("Transação cancelada, saldo ficaria negativo"+saldo);
		if(saldo<100) System.out.println("ALERTA DE SALDO BAIXO"+ saldo);
	}
	
	
	/**
	 * Método que pode imprimir toda a movimentação na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param int 1 ou 2 como opção
	 * 
	 * **/
	public void ExibirExtratoMensal(String cpf, int op) {
		String dataFormatada = FormatarData.formatarAnoMes(new Date());
		List<Movimentacao> movimentacaos = dao.buscarPorCpfData(cpf,dataFormatada);
		switch (op) {
		case 1:
			for (Movimentacao movimentacao : movimentacaos) {
				System.out.println(movimentacao.toString());
			}
			break;
		case 2:
			for (Movimentacao movimentacao : movimentacaos) {
				System.out.println(movimentacao.toStringExtrato());
			}
			break;
		}
	}
	
	/**
	 * Método que pode imprimir toda a movimentação na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param String data inicial no formato "yyyy-MM-dd"
	 * @param String data final formato "yyyy-MM-dd"
	 * @param int 1 ou 2 como opção
	 * **/
	public void ExibirExtratoPeriodico(String cpf, String dataInicial, String dataFinal, int op) throws ParseException {		
		if(FormatarData.formatarStringDate(dataFinal).before(FormatarData.formatarStringDate(dataInicial))) throw new Error ("A data final não pode ser antes da data inicial");
		List<Movimentacao> movimentacaos = dao.buscarPorCpfAteData(cpf,dataInicial, dataFinal);
		switch (op) {
		case 1:
			for (Movimentacao movimentacao : movimentacaos) {
				System.out.println(movimentacao.toString());
			}
			break;
		case 2:
			for (Movimentacao movimentacao : movimentacaos) {
				System.out.println(movimentacao.toStringExtrato());
			}
			break;
	}
	}
	
	public Movimentacao transferencia(Movimentacao pagador, Movimentacao destinatario) {
		if(pagador.getTipoTransacao().equalsIgnoreCase("Transferência")) {
			pagador.setTipoTransacao("Pagamento");
			pagador = validarCompleto(pagador);
			destinatario.setTipoTransacao("Depósito");
			destinatario.setValor(Math.abs(pagador.getValor()));
		}
		else throw new Error ("Tipo de operação inválido");
		destinatario = validarCompleto(destinatario);
		dao.inserir(pagador);
		return dao.inserir(destinatario);
		
	}
	
//	public void testeFraude(Movimentacao conta) {
//		int total = dao.buscarPorCpf(conta.getCpfCorrentista()).size();
//		int numeroOperacoes = dao.buscarPorCpfTransacao(conta.getCpfCorrentista(), conta.getTipoTransacao()).size();
//		double val = dao.buscarSaldoTipo(conta.getCpfCorrentista(), conta.getTipoTransacao());
//		double valMedio = val/numeroOperacoes;
//		double media = numeroOperacoes / total;
//	}
	
	//métodos exclusivos da classe utilizar o private


}
	

