package controle;

import entidade.Conta;
import servico.ContaService;

public class ContaController {
		
	ContaService service = new ContaService();
	
	public Conta inserir(Conta conta) {
		return service.inserir(conta);
	}
	
	public Conta alterar(Conta conta) {
		return service.alterar(conta);
	}
	
	public void excluir (Conta conta) {
		service.excluir(conta);
	}
	
}
