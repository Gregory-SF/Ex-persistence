package servico;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import dao.AbstractDAO;
import dao.ClienteDAO;
import dao.ContaDAO;
import entidade.Conta;
import entidade.ContaTipo;
import util.CalcularJuros;
import util.FormatarData;

public class ContaService implements BaseService<Conta>{
	ContaDAO dao = new ContaDAO();
	ClienteDAO clientedao = new ClienteDAO();

	@Override
	public AbstractDAO<Conta> getDAO() {
		return dao;
	}
	
	@Override
	public Conta inserir(Conta conta) {
		conta.setContaAtiva(true);
		if(!validarDadosIguaisCliente(conta)) criarCliente(conta);
		if(dao.verificarContasPorCliente(conta.getCliente().getId())==3) throw new Error("\nCliente já atingiu o número máximo de contas");
		return dao.inserir(conta);
	}
	
	@Override
	public Conta alterar(Conta conta) {
		if(!validarDadosIguaisCliente(conta)) throw new Error("Não existe um cliente com essas informações");
		return dao.alterar(conta);
	}
	
	@Override
	public void excluir(Long id) {
		if(!dao.buscarPorId(id).isContaAtiva()) dao.excluir(validarDadosIguaisConta(dao.buscarPorId(id)));
	}	
	
	private Long validarDadosIguaisConta(Conta conta) {
		if(!validarDadosIguaisCliente(conta)) throw new Error("Não existe um cliente com essas informações");
		if(!conta.equals(dao.buscarPorId(conta.getId()))) throw new Error("Informações da conta não batem");
		return conta.getId();
	}
	
	private boolean validarDadosIguaisCliente(Conta conta) {
		try {
			conta.setCliente(clientedao.buscarPorCpf(conta.getCliente().getCpfCliente()));
			if(!conta.getCliente().equals(clientedao.buscarPorCpf(conta.getCliente().getCpfCliente()))) return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void criarCliente(Conta conta) {
		System.out.println("Não existe nehum cliente com essas inforções!\nCriando novo cliente");
		try {
			conta.getCliente().setDataCriacao(new Date());
			conta.getCliente().setDataAlteracao(new Date());
			conta.setCliente(clientedao.inserir(conta.getCliente()));
		} catch (Exception e) {
			throw new Error ("\nNão foi possível criar um cliente!\nFavor inserir todas as informações necessárias");
		}
	}
	
	public double calcularContaPoupanca(Conta conta) {
		double saldo = validarContaPoupança(conta);
		LocalDate inicio = LocalDate.parse(FormatarData.formatarAnoMesDia(conta.getDataCriacao()));
		int meses = Period.between(inicio, LocalDate.now()).getMonths();
		return CalcularJuros.JurosCompostos(saldo, 0.002, meses);
	}
	
	
	/** Função que valida a Conta Poupança e retorna o saldo
	 * 
	 * @param Conta
	 * @Returns double saldo
	 * 
	 * */
	private double validarContaPoupança(Conta conta) {
		if(conta.getTipoConta()!=ContaTipo.POUPANCA) throw new Error ("Não foi inserido uma conta poupança");
		String data = FormatarData.formatarAnoMes(new Date());
		double saldo = dao.buscarSaldoContaPoupanca(conta.getId(),data);
		if(saldo<=0) throw new Error ("Saldo zerado ou negativo. Não é possível calcular rendimento");
		return saldo;
	}
	
	public double calcularCredito(Conta conta) throws ParseException {
		String datainicial = dao.BuscarTresMesesAntes(FormatarData.formatarAnoMesDia(new Date()));
		System.out.println(datainicial);
		double credito = dao.buscarCredito(conta.getId(), datainicial, dao.BuscarUltimoDiaMesPassado(FormatarData.formatarAnoMesDia(new Date())));
		return credito;
	}
}
