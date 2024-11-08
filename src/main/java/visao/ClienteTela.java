package visao;

import java.util.Date;

import controle.ClienteController;
import entidade.Cliente;

public class ClienteTela {
		
	public static void main(String[] args) {
		ClienteController controle = new ClienteController();
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("113.186.059-46");
		//cliente.setEmail("als@toma.com");
		cliente.setNomeCliente("Leandro Menoni");
		cliente.setRgCliente("31.452.124-1");
		//cliente.setTelefone("93293-2314");
		cliente.setDataCriacao(new Date());
		
		controle.inserir(cliente);
		//controle.alterar(cliente);
		}
	}
