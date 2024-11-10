package visao;

import java.util.Date;

import controle.ClienteController;
import entidade.Cliente;

public class ClienteTela {
		
	public static void main(String[] args) {
		ClienteController controle = new ClienteController();
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("125.082.889-93");
		//cliente.setEmail("als@toma.com");
		cliente.setNomeCliente("Pedro Costa");
		cliente.setRgCliente("12.345.123-1");
		//cliente.setTelefone("93293-2314");
		cliente.setDataCriacao(new Date());
		
		controle.inserir(cliente);
		//controle.alterar(cliente);
		}
	}
