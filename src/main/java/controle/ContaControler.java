package controle;

import java.text.ParseException;

import entidade.Conta;
import servico.ContaService;

public class ContaControler {
	
	ContaService service = new ContaService();
	
	
	public Conta inserir(Conta conta) {
		return service.inserir(conta);
	}
	
	public void exibirExtratoMensal(String cpf, int op) {
		service.ExibirExtratoMensal(cpf, op);
	}
	
	public void exibirExtratoPeriodico(String cpf, String dataInicial,String dataFinal, int op) throws ParseException {
		service.ExibirExtratoPeriodico(cpf, dataInicial, dataFinal, op);
	}
	
	public Conta transferencia(Conta pag, Conta rec) {
		return service.transferencia(pag, rec);
	}
}
