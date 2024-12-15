package controle;

import entidade.Movimentacao;
import servico.ContaService;
import servico.MovimentacaoService;

public class MovimentacaoController {
	
	MovimentacaoService service = new MovimentacaoService();
	ContaService conService = new ContaService();
	
	public Movimentacao inserir(Movimentacao movimentacao) {
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
	}
	
	public void calcularContaPoupanca(Movimentacao movimentacao) {
		double lucro = conService.calcularContaPoupanca(movimentacao.getConta());
		service.depositarContaPoupanca(movimentacao, lucro);
	}
}
