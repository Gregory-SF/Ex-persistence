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
		conta.setNumeroConta("4");
		conta.setTipoConta(ContaTipo.SALARIO);
		cliente.setCpfCliente("113.186.059-46");
//		cliente.setNomeCliente("Laryssa");
//		cliente.setRgCliente("11.555.000-1");
		conta.setCliente(cliente);
		
		
		controle.inserir(conta);		
	}
}
