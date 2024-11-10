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
		//if(!conta.)
		//return clienteDAO.buscarPorCpf(conta.getCliente().getCpfCliente());
		return conta.getId();
	}
	
	private boolean validarDadosIguaisContaCliente(Conta conta) {
		if(!conta.getCliente().equals(clientedao.buscarPorCpf(conta.getCliente().getCpfCliente()))) return false;
		return true;
	}
	
	private boolean buscarPorCpf(String cpf, Conta conta) {
		try {
			conta.setCliente(clientedao.buscarPorCpf(cpf));
			return true;
		}
		catch (Exception e) {
			return false;
			//throw new Error ("Não existe cliente com esse cpf");
		}
	}
	
}
