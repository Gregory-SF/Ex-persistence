package teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entidade.Cliente;
import util.FormatarData;

public class Teste {
	public static void main(String[] args) throws ParseException {
		Cliente cliente = new Cliente();
		cliente.setCpfCliente("121.825.899-37");
		//cliente.setDataAlteracao("pagar de 13,00 no dia 10/10/24");
		cliente.setNomeCliente("Gregory");
		//cliente.setDataCriacao("Saque");
		cliente.setDataAlteracao(new Date());
		cliente.setDataCriacao(new Date());
		cliente.setEmail("gregory16704@gmail.com");	
		cliente.setRgCliente("1234");	
		cliente.setTelefone("98765");	
		Cliente clienteNovo = new Cliente();
		clienteNovo.setCpfCliente("121.825.899-37");
		clienteNovo.setNomeCliente("Gregory");
		clienteNovo.setId(cliente.getId());
		clienteNovo.setDataAlteracao(cliente.getDataAlteracao());
		clienteNovo.setDataCriacao(cliente.getDataCriacao());
		clienteNovo.setEmail("gregory16704@gmail.com");	
		clienteNovo.setRgCliente("1234");	
		clienteNovo.setTelefone("98765");	
		
		if(clienteNovo.getCpfCliente().equals(clienteNovo)) System.out.println("eba");
		else System.out.println("porra porra porra");
		}
}
