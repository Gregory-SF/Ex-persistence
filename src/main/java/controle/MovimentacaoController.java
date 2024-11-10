package controle;

import java.text.ParseException;

import entidade.Movimentacao;
import servico.MovimentacaoService;

public class MovimentacaoController {
	
	MovimentacaoService service = new MovimentacaoService();
	
	
	public Movimentacao inserir(Movimentacao movimentacao) {
		return service.inserir(movimentacao);
	}
	
	public void exibirExtratoMensal(Long id, int op) {
		service.ExibirExtratoMensal(id, op);
	}
	
	public void exibirExtratoPeriodico(Long id, String dataInicial,String dataFinal, int op) throws ParseException {
		service.ExibirExtratoPeriodico(id, dataInicial, dataFinal, op);
	}
	
//	public Movimentacao transferencia(Movimentacao pag, Movimentacao rec) {
//		return service.transferencia(pag, rec);
//	}
}
