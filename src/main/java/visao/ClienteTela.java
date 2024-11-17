package visao;

import java.util.Date;

import controle.ClienteController;
import entidade.Cliente;

public class ClienteTela {
		
	public static void main(String[] args) {
		ClienteController controle = new ClienteController();
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("114.654.149-08");
		//cliente.setEmail("als@toma.com");
		cliente.setNomeCliente("Isaque Dias");
		cliente.setRgCliente("12.345.133-6");
		//cliente.setTelefone("93293-2314");
		cliente.setDataCriacao(new Date());
		
		controle.inserir(cliente);
		//controle.alterar(cliente);
		}
	}
