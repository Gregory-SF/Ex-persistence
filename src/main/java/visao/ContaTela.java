package visao;

import java.util.Date;

import controle.ContaController;
import entidade.Conta;
import entidade.ContaTipo;
import entidade.Cliente;

public class ContaTela {
	
	public static void main(String[] args) {
		ContaController controle = new ContaController();
		
		Cliente cliente = new Cliente();
		Conta conta = new Conta();
		
		conta.setContaAtiva(false);
		conta.setDataCriacao(new Date());
		conta.setDataAlteracao(new Date());
		conta.setNumeroConta("5");
		conta.setTipoConta(ContaTipo.CORRENTE);
		cliente.setCpfCliente("114.654.149-08");
		conta.setCliente(cliente);
		controle.inserir(conta);		
		//controle.exibirExtratoMensal("123.456.789-09",2);
		//controle.exibirExtratoPeriodico("123.456.789-09", "2024-10-15","2024-10-18", 2);
		//System.out.println(controle.calcularCredito(conta));
	}
}
