package visao;

import java.util.Date;

import controle.ClienteController;
import entidade.Cliente;

public class ClienteTela {
		
	public static void main(String[] args) {
		ClienteController controle = new ClienteController();
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("178.304.570-18");
		//cliente.setEmail("als@toma.com");
		cliente.setNomeCliente("Gabriela Colombia");
		cliente.setRgCliente("12.325.133-9");
		//cliente.setTelefone("93293-2314");
		cliente.setDataCriacao(new Date());
		
		controle.inserir(cliente);
		//controle.alterar(cliente);
		}
	}
