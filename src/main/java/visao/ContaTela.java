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
		conta.setTipoConta(ContaTipo.POUPANCA);
		cliente.setCpfCliente("114.654.149-08");
//		cliente.setNomeCliente("Laryssa");
//		cliente.setRgCliente("11.555.000-1");
		conta.setCliente(cliente);
		
		System.out.println(controle.calcularCredito(conta));
		//controle.inserir(conta);		
	}
}
