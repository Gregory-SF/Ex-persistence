package servico;

import dao.ClienteDAO;
import dao.ContaDAO;
import entidade.Conta;

public class ContaService {
	ContaDAO dao = new ContaDAO();
	ClienteDAO clientedao = new ClienteDAO();


	public Conta inserir(Conta conta) {
		conta.setContaAtiva(true);
		buscarPorCpf(conta.getCliente().getCpfCliente(), conta);
		return dao.inserir(conta);
	}
	
	public Conta alterar(Conta conta) {
		if(!validarDadosIguaisContaCliente(conta)) throw new Error ("\nDados não correspondem a nenhum conta existente");
		//return dao.alterar(buscarIdPeloCPf(conta),conta);
		return dao.alterar(conta.getId(),conta);
		
	}
	
	public void excluir(Conta conta) {
		if(!conta.isContaAtiva()) dao.excluir(conta.getId());
	}
	
	private Long validarDadosIguaisConta(Conta conta) {
		//opção 1
		if(!validarDadosIguaisContaCliente(conta)) throw new Error("Não existe um cliente com essas informações");
		//return clienteDAO.buscarPorCpf(conta.getCliente().getCpfCliente());
		return conta.getId();
	}
	
	private boolean validarDadosIguaisContaCliente(Conta conta) {
		ClienteDAO clienteDAO = new ClienteDAO();
		if(!conta.getCliente().equals(clienteDAO.buscarPorCpf(conta.getCliente().getCpfCliente()))) return false;
		return true;
	}
	
	private void buscarPorCpf(String cpf, Conta conta) {
		conta.setCliente(clientedao.buscarPorCpf(cpf));
	}
	
}
