package servico;

import dao.ClienteDAO;
import entidade.Cliente;
import util.ValidacaoCpf;

public class ClienteService {
	ClienteDAO dao = new ClienteDAO();
	
	public Cliente inserir(Cliente cliente) {
		if(!ValidacaoCpf.validarCpf(cliente.getCpfCliente())) throw new Error("CPF inválido");
		return dao.inserir(cliente);
	}
	
	public Cliente alterar(Cliente cliente) {
		if(!validarDadosIguaisCliente(cliente)) throw new Error ("\nDados não correspondem a nenhum cliente existente");
		return dao.alterar(buscarIdPeloCpf(cliente),cliente);
	}
	
	public void excluir(Cliente cliente) {
		dao.excluir(buscarIdPeloCpf(cliente));
	}
	
	private Long buscarIdPeloCpf(Cliente cliente) {
		if(!validarDadosIguaisCliente(cliente)) throw new Error ("\nDados não correspondem a nenhum cliente existente");
		return dao.buscarPorCpf(cliente.getCpfCliente()).getId();
	}
	
	private boolean validarDadosIguaisCliente(Cliente cliente) {
		Cliente clienteDados = dao.buscarPorCpf(cliente.getCpfCliente());
		if(!clienteDados.equals(cliente)) return false;
		return true;
	}
}
