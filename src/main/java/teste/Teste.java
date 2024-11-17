package teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import entidade.Cliente;
import entidade.Movimentacao;
import servico.ClienteService;
import servico.ContaService;
import servico.MovimentacaoService;
import dao.ClienteDAO;
import dao.ContaDAO;
import dao.MovimentacaoDAO;
import util.CalcularJuros;
import util.FormatarData;
import util.ValidacaoCpf;

public class Teste {
	public static void main(String[] args) throws ParseException {
		Cliente cliente = new Cliente();
		ContaDAO condao = new ContaDAO();
		MovimentacaoDAO movdao = new MovimentacaoDAO();
		Movimentacao mov = new Movimentacao();
//		System.out.println(movdao.buscarSaldoContaPoupanca(1L));
		MovimentacaoService movser = new MovimentacaoService();
//		System.out.println(movdao.buscarSaldo(1L));
//		cliente.setCpfCliente("121.825.897-37");
//		if(ValidacaoCpf.validarCpf(cliente.getCpfCliente()));
//		//cldao.buscarPorCpf(cliente.getCpfCliente());
//		//cliente.setDataAlteracao("pagar de 13,00 no dia 10/10/24");
//		cliente.setNomeCliente("Gregory");
//		//cliente.setDataCriacao("Saque");
//		cliente.setDataAlteracao(new Date());
//		cliente.setDataCriacao(new Date());
//		cliente.setEmail("gregory16704@gmail.com");	
//		cliente.setRgCliente("1234");	
//		cliente.setTelefone("98765");	
//		Cliente clienteNovo = new Cliente();
//		clienteNovo.setCpfCliente("121.825.899-37");
//		clienteNovo.setNomeCliente("Gregory");
//		clienteNovo.setId(cliente.getId());
//		clienteNovo.setDataAlteracao(cliente.getDataAlteracao());
//		clienteNovo.setDataCriacao(cliente.getDataCriacao());
//		clienteNovo.setEmail("gregory16704@gmail.com");	
//		clienteNovo.setRgCliente("1234");	
//		clienteNovo.setTelefone("98765");	
		//S;ystem.out.println(movdao.buscarSaldo(1L));
		//CalcularJuros.JurosCompostos(saldo, 0.2, meses);
		//cliente.setDataAlteracao(FormatarData.formatarMesAnterior("2024-12-01"));
		//System.out.println(FormatarData.formatarMesPosterior(data));
		System.out.println(condao.BuscarTresMesesAntes("2025-01-30"));
		System.out.println(condao.buscarCredito(7L, condao.BuscarTresMesesAntes("2025-01-30"), "2025-01-30"));
		
	}
}
