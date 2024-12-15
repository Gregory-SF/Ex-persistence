package controle;

import java.text.ParseException;

import entidade.Conta;
import servico.ContaService;
import servico.MovimentacaoService;

public class ContaController {
		
	ContaService service = new ContaService();
	MovimentacaoService movService = new MovimentacaoService();
	
	public Conta inserir(Conta conta) {
		return service.inserir(conta);
	}
	
	public Conta alterar(Conta conta) {
		return service.alterar(conta);
	}
	
	public void excluir (Conta conta) {
		service.excluir(conta.getId());
	}
	
	public double calcularPoupanca(Conta conta) {
		return service.calcularContaPoupanca(conta);
	}
	
	public double calcularCredito(Conta conta) {
		try {
			return service.calcularCredito(conta);
		} catch (ParseException e) {}
		throw new Error("Houve um erro");
	}
	
	public void exibirExtratoMensal(Long id, int op) {
		movService.ExibirExtratoMensal(id, op);
	}
	
	public void exibirExtratoPeriodico(Long id, String dataInicial,String dataFinal, int op) throws ParseException {
		movService.ExibirExtratoPeriodico(id, dataInicial, dataFinal, op);
	}
	
}
