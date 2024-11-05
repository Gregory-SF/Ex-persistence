package controle;

import java.text.ParseException;

import entidade.Movimentacao;
import servico.MovimentacaoService;

public class MovimentacaoController {
	
	MovimentacaoService service = new MovimentacaoService();
	
	
	public Movimentacao inserir(Movimentacao movimentacao) {
		return service.inserir(movimentacao);
	}
	
	public void exibirExtratoMensal(String cpf, int op) {
		service.ExibirExtratoMensal(cpf, op);
	}
	
	public void exibirExtratoPeriodico(String cpf, String dataInicial,String dataFinal, int op) throws ParseException {
		service.ExibirExtratoPeriodico(cpf, dataInicial, dataFinal, op);
	}
	
	public Movimentacao transferencia(Movimentacao pag, Movimentacao rec) {
		return service.transferencia(pag, rec);
	}
}
