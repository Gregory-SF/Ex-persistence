package visao;

import java.util.Date;

import controle.ClienteController;
import entidade.Cliente;

public class ClienteTela {
		
	public static void main(String[] args) {
		ClienteController controle = new ClienteController();
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("123.456.789-09");
		cliente.setEmail("als@toma.com");
		cliente.setNomeCliente("junin");
		cliente.setRgCliente("32.456.123-1");
		cliente.setTelefone("93293-2314");
		cliente.setDataCriacao(new Date());
		
		//controle.inserir(cliente);
		//controle.alterar(cliente);
		}
	}
