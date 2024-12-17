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
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		Cliente cliente = new Cliente();
		Movimentacao movimentacao = new Movimentacao();
		ContaService conSer = new ContaService();
		MovimentacaoService movSer = new MovimentacaoService();
		ContaDAO condao = new ContaDAO();
		MovimentacaoDAO movdao = new MovimentacaoDAO();
		
		
		movSer.ExibirExtratoMensal(8L, 1);
//		movSer.ExibirExtratoPeriodico(4L, "2024-08-01", "2025-01-30" ,1);
//		System.out.println(condao.BuscarUltimoDiaMesPassado("2024-11-21"));
//		System.out.println(condao.BuscarTresMesesAntes("2024-11-30"));
//		System.out.println(conSer.calcularCredito(condao.buscarPorId(7l)));
//		System.out.println(condao.buscarCredito(7L, "2024-11-01", "2025-01"));
//		System.out.println(condao.BuscarTresMesesAntes("2024-12-15"));
//		movSer.ExibirExtratoPeriodico(7L,"2024-11-01", "2025-02-01",1);
//		System.out.println(condao.buscarCredito(7L, "2024-11-01", "2025-02-01"));
//		System.out.println(conSer.calcularCredito(condao.buscarPorId(7L)));
//		System.out.println(conser.calcularContapoupanca(condao.buscarPorId(7L)));
//		System.out.println(movimentacao));
//		for (Movimentacao mov: movdao.buscarPorIdConta(2L)) {
//			System.out.println(mov.toString());
//		}
//		System.out.println(movdao.buscarSaldoContaPoupanca(1L));
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
//		System.out.println(condao.BuscarTresMesesAntes("2025-01-30"));
//		System.out.println(condao.buscarCredito(7L, condao.BuscarTresMesesAntes("2025-01-30"), "2025-01-30"));
		
	}
}
