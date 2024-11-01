package servico;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dao.ContaDAO;
import entidade.Conta;
import util.FormatarData;
import util.ValidacaoCpf;

public class ContaService {
	ContaDAO dao = new ContaDAO();
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

	
	public Conta inserir(Conta conta) {
		conta = validarCompleto(conta);
		conta.setDescricao(conta.getTipoTransacao().toLowerCase()+"de R$"+conta.getValor());
		return dao.inserir(conta);
	}
	
	private Conta validarCompleto(Conta conta) {
		if(!ValidacaoCpf.validarCpf(conta.getCpfCorrentista())) throw new Error("CPF inválido");
		if(conta.getValor()==0) throw new Error("Valor não pode ser nulo");
		limiteOperacoes(conta);
		conta = validarTipoTransacao(conta);
		validarSaldo(conta);
		return conta;
	}
	
	private Conta validarTipoTransacao(Conta conta) {
		for (TipoTransacao transacao: TipoTransacao.values()) {
			if (conta.getTipoTransacao().equalsIgnoreCase(transacao.operacao)) {
				conta.setTipoTransacao(transacao.getTipoTransacao());
				if(transacao.operacao.equals("Pagamento") || transacao.operacao.equals("Pix")) {
					conta.setValor(conta.getValor()*-1-5);
					if(transacao.operacao.equals("Pix")) validarPix(conta);
				}
				else if(transacao.operacao.equals("Saque")) {
					conta.setValor(conta.getValor()*-1-2);
					validarSaque(conta);
				}
				return conta;					
			}
		}
		throw new Error ("Tipo de transação inválida");
	}
	
	private void validarPix(Conta conta) {
		int hora = FormatarData.formatarHora(conta.getDataTransacao());
		if(hora>22 || hora<6) throw new Error("Fora do horário permitido");
		if(Math.abs(conta.getValor())>300.00) throw new Error("Valor máximo do pix ultrapassado");
	}
	
	private void validarSaque(Conta conta) {
		String dataFormatada = FormatarData.formatarAnoMesDia(conta.getDataTransacao());
		double valor = dao.buscarSaldoSaque(conta.getCpfCorrentista(),dataFormatada);
		valor += conta.getValor();
		System.out.println(valor);
		if(valor<-5000) throw new Error("Transação cancelada, limite diário de saque alcançado");	
	}
	
	private void limiteOperacoes(Conta conta) {
		String dataFormatada = FormatarData.formatarAnoMesDia(conta.getDataTransacao());
		if(dao.buscarPorCpfData(conta.getCpfCorrentista(),dataFormatada).size()==10) throw new Error("Limite máximo de 10 transações atingido");
	}
	
	private void validarSaldo(Conta conta) {
		double saldo = dao.buscarSaldo(conta.getCpfCorrentista())+conta.getValor();
		if(saldo<0) throw new Error("Transação cancelada, saldo ficaria negativo"+saldo);
		if(saldo<100) System.out.println("ALERTA DE SALDO BAIXO"+ saldo);
	}
	
	
	/**
	 * Método que pode imprimir toda a conta na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param int 1 ou 2 como opção
	 * 
	 * **/
	public void ExibirExtratoMensal(String cpf, int op) {
		String dataFormatada = FormatarData.formatarAnoMes(new Date());
		List<Conta> contas = dao.buscarPorCpfData(cpf,dataFormatada);
		switch (op) {
		case 1:
			for (Conta conta : contas) {
				System.out.println(conta.toString());
			}
			break;
		case 2:
			for (Conta conta : contas) {
				System.out.println(conta.toStringExtrato());
			}
			break;
		}
	}
	
	/**
	 * Método que pode imprimir toda a conta na opção 1 ou imprimir só os valores na opção 2
	 * @param String cpf
	 * @param String data inicial no formato "yyyy-MM-dd"
	 * @param String data final formato "yyyy-MM-dd"
	 * @param int 1 ou 2 como opção
	 * **/
	public void ExibirExtratoPeriodico(String cpf, String dataInicial, String dataFinal, int op) throws ParseException {		
		if(FormatarData.formatarStringDate(dataFinal).before(FormatarData.formatarStringDate(dataInicial))) throw new Error ("A data final não pode ser antes da data inicial");
		List<Conta> contas = dao.buscarPorCpfAteData(cpf,dataInicial, dataFinal);
		switch (op) {
		case 1:
			for (Conta conta : contas) {
				System.out.println(conta.toString());
			}
			break;
		case 2:
			for (Conta conta : contas) {
				System.out.println(conta.toStringExtrato());
			}
			break;
	}
	}
	
	public Conta transferencia(Conta pagador, Conta destinatario) {
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
	
//	public void testeFraude(Conta conta) {
//		int total = dao.buscarPorCpf(conta.getCpfCorrentista()).size();
//		int numeroOperacoes = dao.buscarPorCpfTransacao(conta.getCpfCorrentista(), conta.getTipoTransacao()).size();
//		double val = dao.buscarSaldoTipo(conta.getCpfCorrentista(), conta.getTipoTransacao());
//		double valMedio = val/numeroOperacoes;
//		double media = numeroOperacoes / total;
//	}
	
	//métodos exclusivos da classe utilizar o private


}
	

