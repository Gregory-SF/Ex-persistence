package servico;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import dao.ContaDAO;
import dao.MovimentacaoDAO;
import entidade.Movimentacao;
import entidade.TransacaoTipo;
import util.CalcularJuros;
import util.FormatarData;

public class MovimentacaoService {
	MovimentacaoDAO dao = new MovimentacaoDAO();
	ContaDAO contaDAO = new ContaDAO();

	
	public Movimentacao cartaoCredito(Movimentacao movimentacao) {
		if(1==1) throw new Error("Funcão não implementada ainda teehee");
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao cartaoDebito(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1);
		validarCompleto(movimentacao);
		dao.inserir(movimentacao);
		return dao.inserir(validarCartaoDebito(movimentacao));
	}
	
	public Movimentacao deposito(Movimentacao movimentacao) {
		validarCompleto(movimentacao);;
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao pagamento(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-5);
		validarCompleto(movimentacao);
		return dao.inserir(movimentacao);
	}
	
	public Movimentacao pix(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-5);
		validarCompleto(movimentacao);
		validarPix(movimentacao);
		return dao.inserir(movimentacao);
	}

	public Movimentacao saque(Movimentacao movimentacao) {
		movimentacao.setValor(movimentacao.getValor()*-1-2);
		validarCompleto(movimentacao);
		validarSaque(movimentacao);
		return dao.inserir(movimentacao);
	}
	
//	public Movimentacao transferencia(Movimentacao pagador, Movimentacao destinatario) {
//	if(pagador.getTipoTransacao()==TransacaoTipo.TRANSFERENCIA) {
//		//pagador.setTipoTransacao("Pagamento");
//		validarCompleto(pagador);
//		destinatario.setTipoTransacao(TransacaoTipo.DEPOSITO);
//		destinatario.setValor(Math.abs(pagador.getValor()));
//	}
//	else throw new Error ("Tipo de operação inválido");
//	validarCompleto(destinatario);
//	dao.inserir(pagador);
//	return dao.inserir(destinatario);
//	
//}
	
	private void validarCompleto(Movimentacao movimentacao) {
		if(movimentacao.getValor()==0) throw new Error("Valor não pode ser nulo");
		if(!buscarPorId(movimentacao.getConta().getId(), movimentacao)) throw new Error ("Não existe essa conta");
		validarSaldo(movimentacao);
		validarLimiteOperacoes(movimentacao);
		//if(contaDAO.verificarContasPorCliente(movimentacao.getConta().getCliente().getId())>3) throw new Error();
		movimentacao.setDescricao(movimentacao.getTipoTransacao()+" de R$"+movimentacao.getValor());
	}
	
	private void validarLimiteOperacoes(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		if(dao.buscarPorIdData(movimentacao.getConta().getId(),dataFormatada).size()==10) throw new Error("Limite máximo de 10 transações atingido");
	}
	
	private void validarSaldo(Movimentacao movimentacao) {
		double saldo = dao.buscarSaldo(movimentacao.getConta().getId())+movimentacao.getValor();
		if(saldo<0) throw new Error("Transação cancelada, saldo ficaria negativo"+saldo);
		if(saldo<100) System.out.println("ALERTA DE SALDO BAIXO"+ saldo);
	}
	
	private Movimentacao validarCartaoDebito(Movimentacao movimentacao) {
		Movimentacao operacaoCashBack = new Movimentacao();
		operacaoCashBack.setConta(movimentacao.getConta());
		operacaoCashBack.setTipoTransacao(movimentacao.getTipoTransacao());
		operacaoCashBack.setValor(Math.abs(movimentacao.getValor()) *0.2/100);
		operacaoCashBack.setDescricao("Cashback de 0,2%");
		try {
			operacaoCashBack.setDataTransacao(FormatarData.formatarMesPosterior(movimentacao.getDataTransacao()));
		} catch (ParseException e) {}
		return operacaoCashBack;
	}
	
	private void validarPix(Movimentacao movimentacao) {
		int hora = FormatarData.formatarHora(movimentacao.getDataTransacao());
		if(hora>22 || hora<6) throw new Error("Fora do horário permitido");
		if(Math.abs(movimentacao.getValor())>300.00) throw new Error("Valor máximo do pix ultrapassado");
	}
	
	private void validarSaque(Movimentacao movimentacao) {
		String dataFormatada = FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao());
		double valor = dao.buscarSaldoSaque(movimentacao.getConta().getId(),dataFormatada)+movimentacao.getValor();
		if(valor<-5000) throw new Error("Transação cancelada, limite diário de saque alcançado");	
	}
	
	/**
	 * Método que pode imprimir toda a movimentação na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param int 1 ou 2 como opção
	 * 
	 * **/
	public void ExibirExtratoMensal(Long id, int op) {
		String dataFormatada = FormatarData.formatarAnoMes(new Date());
		List<Movimentacao> movimentacoes = dao.buscarPorIdData(id,dataFormatada);
		switch (op) {
		case 1:
			for (Movimentacao movimentacao : movimentacoes) {
				System.out.println(movimentacao.toString());
			}
			break;
		case 2:
			for (Movimentacao movimentacao : movimentacoes) {
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
		List<Movimentacao> movimentacoes = dao.buscarPorIdContaAteData(id,dataInicial, dataFinal);
		switch (op) {
		case 1:
			for (Movimentacao movimentacao : movimentacoes) {
				System.out.println(movimentacao.toString());
			}
			break;
		case 2:
			for (Movimentacao movimentacao : movimentacoes) {
				System.out.println(movimentacao.toStringExtrato());
			}
			break;
		}
	}
	
	private boolean buscarPorId(Long id, Movimentacao movimentacao) {
		try {
			movimentacao.setConta(contaDAO.buscarPorId(id));
			return true;
		}
		catch(Exception e) {
			throw new Error ("Conta inexistente");
			//throw new Exception("Conta não existe");
		}
	}
	
	public Movimentacao calcularContapoupanca(Movimentacao movimentacao) {
		double saldo = dao.buscarSaldoContaPoupanca(movimentacao.getConta().getId());
		if(saldo<=0) throw new Error ("Saldo zerado ou negativo. Não é possível calcular rendimento");
		LocalDate inicio = LocalDate.parse(FormatarData.formatarAnoMesDia(movimentacao.getDataTransacao()));
		int meses = Period.between(inicio, LocalDate.now()).getMonths();
		Movimentacao rendimento = new Movimentacao();
		rendimento.setValor(CalcularJuros.JurosCompostos(saldo, 0.2, meses));
		rendimento.setConta(movimentacao.getConta());
		rendimento.setDataTransacao(new Date());
		rendimento.setDescricao("Rendimento dos juros compostos da Conta Poupança");
		rendimento.setTipoTransacao(TransacaoTipo.DEPOSITO);
		return dao.inserir(movimentacao);
		
	}
	
//	public void testeFraude(Movimentacao conta) {
//		int total = dao.buscarPorCpf(conta.getCpfCorrentista()).size();
//		int numeroOperacoes = dao.buscarPorCpfTransacao(conta.getCpfCorrentista(), conta.getTipoTransacao()).size();
//		double val = dao.buscarSaldoTipo(conta.getCpfCorrentista(), conta.getTipoTransacao());
//		double valMedio = val/numeroOperacoes;
//		double media = numeroOperacoes / total;
//	}
	
}
	

