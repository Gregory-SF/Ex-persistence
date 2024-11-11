package servico;

import java.util.Date;

import dao.ClienteDAO;
import dao.ContaDAO;
import entidade.Conta;

public class ContaService {
	ContaDAO dao = new ContaDAO();
	ClienteDAO clientedao = new ClienteDAO();


	public Conta inserir(Conta conta) {
		conta.setContaAtiva(true);
		if(!validarDadosIguaisCliente(conta)) criarCliente(conta);
		if(dao.verificarContasPorCliente(conta.getCliente().getId())==3) throw new Error("\nCliente já atingiu o número máximo de contas");
		return dao.inserir(conta);
	}
	
	public Conta alterar(Conta conta) {
		return dao.alterar(validarDadosIguaisConta(conta),conta);
	}
	
	public void excluir(Conta conta) {
		if(!conta.isContaAtiva()) dao.excluir(validarDadosIguaisConta(conta));
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
//			conta.setCliente(clientedao.buscarPorCpf(conta.getCliente().getCpfCliente()));
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
}
