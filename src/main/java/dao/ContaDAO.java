package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Cliente;
import entidade.Conta;
import entidade.Movimentacao;

public class ContaDAO extends AbstractDAO<Conta> {
	
	public ContaDAO() {
		super(Conta.class);
	}
	
	/**
	 * Função que retorna a quantidade de contas por cliente
	 * @param Long id_cliente
	 * @return integer
	 * */
	public int verificarContasPorCliente(Long id){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Conta where id_cliente='"+id+"'");
		List<Conta> contas = query.getResultList();
		int quantidade  = contas.size();
		em.close();
		return quantidade;
	}
	
	public double buscarSaldoContaPoupanca(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' and dataTransacao LIKE '"+data+"%'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public String BuscarTresMesesAntes(String data){
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery("select subdate(adddate(last_day('"+data+"'), interval 1 day),interval 3 month)");
		String dataAntiga = query.getSingleResult().toString();
		em.close();
		return dataAntiga;
	}
	
	public double buscarCredito(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' AND dataTransacao BETWEEN '"+dataInicial+" 00:00:00.000' AND '"+dataFinal+" 23:59:59.999'");
		double saldoMedio = 0.;
		try {
			saldoMedio = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldoMedio/3;
	}
}
