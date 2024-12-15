package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Conta;

public class ContaDAO extends AbstractDAO<Conta> {
	
	public ContaDAO() {
		super(Conta.class);
	}
	
	/**
	 * Função que retorna a quantidade de contas por cliente
	 * @param Long id_cliente
	 * @return integer
	 * */
	public int verificarContasPorCliente(Long idCliente){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Conta where id_cliente= :id").setParameter("id", idCliente);
		List<Conta> contas = query.getResultList();
		int quantidade  = contas.size();
		em.close();
		return quantidade;
	}
	
	public double buscarSaldoContaPoupanca(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta= :id and data_transacao LIKE CONCAT(:data,'%')").setParameter("id", idConta).setParameter("data", data);
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarCredito(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valor_operacao) from Movimentacao where id_conta= :id AND data_transacao BETWEEN CONCAT(:dataInicial, ' 00:00:00.000') AND CONCAT(:dataFinal, ' 23:59:59.999')").setParameter("id", idConta).setParameter("dataInicial", dataInicial).setParameter("dataFinal", dataFinal);
		double saldoMedio = 0.;
		try {
			saldoMedio = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldoMedio/3;
	}
	
}
