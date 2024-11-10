package servico;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dao.ContaDAO;
import dao.MovimentacaoDAO;
import entidade.Movimentacao;
import util.FormatarData;

public class MovimentacaoService {
	MovimentacaoDAO dao = new MovimentacaoDAO();
	ContaDAO contadao = new ContaDAO();

	public Movimentacao saque(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-2);
		validarSaldo(movimentacao);
		validarSaque(movimentacao);
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao pix(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-5);
		validarSaldo(movimentacao);
		validarPix(movimentacao);
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao pagamento(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-5);
		validarSaldo(movimentacao);
		return dao.inserir(movimentacao);
	}
	public Movimentacao deposito(Movimentacao movimentacao) {
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao inserir(Movimentacao movimentacao) {
		movimentacao = validarCompleto(movimentacao);
		
		movimentacao.setDescricao(movimentacao.getTipoTransacao()+" de R$"+movimentacao.getValor());
		switch (movimentacao.getTipoTransacao()) {
		case SAQUE:
			return saque(movimentacao);
			
		case PIX:
			return pix(movimentacao);
			
		case PAGAMENTO:
			return pagamento(movimentacao);
		
		case DEPOSITO:
			return deposito(movimentacao);
			
		default:
			throw new Error ("Tipo inválido");
		}
	}
	
	
	private Movimentacao validarCompleto(Movimentacao movimentacao) {
		if(movimentacao.getValor()==0) throw new Error("Valor não pode ser nulo");
		limiteOperacoes(movimentacao);
		if(!buscarPorId(movimentacao.getConta().getId(), movimentacao)) throw new Error ("Não existe essa conta");
		return movimentacao;
	}
	
	private void validarPix(Movimentacao movimentacao) {
		int hora = FormatarData.formatarHora(movimentacao.getDataTransacao());
		if(hora>22 || hora<6) throw new Error("Fora do horário permitido");
		if(Math.abs(movimentacao.getValor())>300.00) throw new Error("Valor máximo do pix ultrapassado");
	}
	
	private void validarSaque(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		double valor = dao.buscarSaldoSaque(movimentacao.getConta().getId(),dataFormatada);
		valor += movimentacao.getValor();
		System.out.println(valor);
		if(valor<-5000) throw new Error("Transação cancelada, limite diário de saque alcançado");	
	}
	
	private void limiteOperacoes(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		if(dao.buscarPorIdData(movimentacao.getConta().getId(),dataFormatada).size()==10) throw new Error("Limite máximo de 10 transações atingido");
	}
	
	private void validarSaldo(Movimentacao movimentacao) {
		double saldo = dao.buscarSaldo(movimentacao.getConta().getId())+movimentacao.getValor();
		if(saldo<0) throw new Error("Transação cancelada, saldo ficaria negativo"+saldo);
		if(saldo<100) System.out.println("ALERTA DE SALDO BAIXO"+ saldo);
	}
	
	
	/**
	 * Método que pode imprimir toda a movimentação na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param int 1 ou 2 como opção
	 * 
	 * **/
	public void ExibirExtratoMensal(Long id, int op) {
		String dataFormatada = FormatarData.formatarAnoMes(new Date());
		List<Movimentacao> movimentacaos = dao.buscarPorIdData(id,dataFormatada);
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
	public void ExibirExtratoPeriodico(Long id, String dataInicial, String dataFinal, int op) throws ParseException {		
		if(FormatarData.formatarStringDate(dataFinal).before(FormatarData.formatarStringDate(dataInicial))) throw new Error ("A data final não pode ser antes da data inicial");
		List<Movimentacao> movimentacaos = dao.buscarPorIdContaAteData(id,dataInicial, dataFinal);
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
	
	private boolean buscarPorId(Long id, Movimentacao movimentacao) {
		try {
			movimentacao.setConta(contadao.buscarPorId(id));
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
//	public Movimentacao transferencia(Movimentacao pagador, Movimentacao destinatario) {
//		if(pagador.getTipoTransacao().equalsIgnoreCase("Transferência")) {
//			pagador.setTipoTransacao("Pagamento");
//			pagador = validarCompleto(pagador);
//			destinatario.setTipoTransacao("Depósito");
//			destinatario.setValor(Math.abs(pagador.getValor()));
//		}
//		else throw new Error ("Tipo de operação inválido");
//		destinatario = validarCompleto(destinatario);
//		dao.inserir(pagador);
//		return dao.inserir(destinatario);
//		
//	}
	
//	public void testeFraude(Movimentacao conta) {
//		int total = dao.buscarPorCpf(conta.getCpfCorrentista()).size();
//		int numeroOperacoes = dao.buscarPorCpfTransacao(conta.getCpfCorrentista(), conta.getTipoTransacao()).size();
//		double val = dao.buscarSaldoTipo(conta.getCpfCorrentista(), conta.getTipoTransacao());
//		double valMedio = val/numeroOperacoes;
//		double media = numeroOperacoes / total;
//	}
	
	//métodos exclusivos da classe utilizar o private


}
	

