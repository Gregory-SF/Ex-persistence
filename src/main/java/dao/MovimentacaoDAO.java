package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Movimentacao;
import entidade.TransacaoTipo;

public class MovimentacaoDAO extends AbstractDAO<Movimentacao> {
	
	public MovimentacaoDAO() {
		super(Movimentacao.class);
	}
	
	public List<Movimentacao> buscarPorIdConta(Long idConta){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta= :id").setParameter("id", idConta);
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaData(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta= :id AND data_transacao LIKE CONCAT(:data,'%')").setParameter("id", idConta).setParameter("data", data);
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaAteData(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta= :id AND data_transacao BETWEEN CONCAT(:dataInicial, ' 00:00:00.000') AND CONCAT(:dataFinal, ' 23:59:59.999')").setParameter("id", idConta).setParameter("dataInicial", dataInicial).setParameter("dataFinal", dataFinal);
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaTransacao(Long idConta, String tipoTrsn){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta= :id AND tipo_transacao= :tipoTrsn").setParameter("id", idConta).setParameter("tipoTrsn", tipoTrsn);
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	
	public double buscarSaldo (Long idConta){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta= :id").setParameter("id", idConta);
		double saldo = 0.;
		try {
			saldo = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoSaque(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta= :id AND data_transacao LIKE CONCAT(:data,'%') AND tipo_transacao='SAQUE'").setParameter("id", idConta).setParameter("data", data);
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}	
}
