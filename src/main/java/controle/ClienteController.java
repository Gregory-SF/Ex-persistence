package controle;

import entidade.Cliente;
import servico.ClienteService;

public class ClienteController {
		
	ClienteService service = new ClienteService();
	
	
	public Cliente inserir(Cliente cliente) {
		return service.inserir(cliente);
	}
	
	public void deletar(Cliente cliente) {
		service.excluir(cliente);
	}
	
	public Cliente alterar(Cliente cliente) {
		return service.alterar(cliente);
	}
	
}


