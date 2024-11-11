package controle;

import java.text.ParseException;

import entidade.Movimentacao;
import servico.ContaService;
import servico.MovimentacaoService;

public class MovimentacaoController {
	
	MovimentacaoService service = new MovimentacaoService();
	ContaService conService = new ContaService();
	
	public Movimentacao inserir(Movimentacao movimentacao) {
//		try {
			switch (movimentacao.getTipoTransacao()) {
			case SAQUE:
				return service.saque(movimentacao);
			case PIX:
				return service.pix(movimentacao);
			case PAGAMENTO:
				return service.pagamento(movimentacao);
			case DEPOSITO:
				return service.deposito(movimentacao);
			case CARTAO_DE_DEBITO:
				return service.cartaoDebito(movimentacao);
			case CARTAO_DE_CREDITO:
				return service.cartaoCredito(movimentacao);
			default:
				throw new Error(":p");
			}
//		} catch(Exception ex) {
//			System.out.println("Conta não existente\nCriando uma nova conta");
//			movimentacao.setConta(conService.inserir(movimentacao.getConta()));
//			return inserir(movimentacao);
//			
//		}
	}
	
	public void exibirExtratoMensal(Long id, int op) {
		service.ExibirExtratoMensal(id, op);
	}
	
	public void exibirExtratoPeriodico(Long id, String dataInicial,String dataFinal, int op) throws ParseException {
		service.ExibirExtratoPeriodico(id, dataInicial, dataFinal, op);
	}
	
	public Movimentacao calcularContaPoupanca(Movimentacao movimentacao) {
		return service.calcularContapoupanca(movimentacao);
	}
}
