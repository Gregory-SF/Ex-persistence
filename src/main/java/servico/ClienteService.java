package servico;

import java.util.Date;

import dao.AbstractDAO;
import dao.ClienteDAO;
import entidade.Cliente;
import util.ValidacaoCpf;

public class ClienteService implements BaseService<Cliente>{
	ClienteDAO dao = new ClienteDAO();
	
	@Override
	public AbstractDAO<Cliente> getDAO(){
		return dao;
	}
	
	@Override
	public Cliente inserir(Cliente cliente) {
		if(!ValidacaoCpf.validarCpf(cliente.getCpfCliente())) throw new Error("CPF inválido");
		cliente.setDataCriacao(new Date());
		return dao.inserir(cliente);
	}
	
	@Override
	public Cliente alterar(Cliente cliente) {
		cliente.setDataAlteracao(new Date());
		cliente.setId(buscarIdPeloCpf(cliente));
		return dao.alterar(cliente);
	}
	
	public void excluir(Cliente cliente) {
		dao.excluir(buscarIdPeloCpf(cliente));
	}
	
	private Long buscarIdPeloCpf(Cliente cliente) {
		if(!validarDadosIguaisCliente(cliente)) throw new Error ("\nDados não correspondem a nenhum cliente existente");
		return dao.buscarPorCpf(cliente.getCpfCliente()).getId();
	}
	
	private boolean validarDadosIguaisCliente(Cliente cliente) {
		try {
			Cliente clienteDados = dao.buscarPorCpf(cliente.getCpfCliente());
			if(clienteDados.equals(cliente)) return true;
			return false;
		}catch(Exception e) {
			return false;
		}
	}
}
