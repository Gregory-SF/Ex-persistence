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
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorTransacao(TransacaoTipo tipoTrsn){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where tipoTransacao='"+tipoTrsn+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdData(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"' AND dataTransacao LIKE '"+data+"%'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaAteData(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"' AND dataTransacao BETWEEN '"+dataInicial+" 00:00:00.000' AND '"+dataFinal+" 23:59:59.999'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaTransacao(Long idConta, String tipoTrsn){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"'" +" AND tipoTransacao='"+tipoTrsn+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	
	public double buscarSaldo (Long idConta){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"'");
		double saldo = 0.;
		try {
			saldo = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoSaque(Long idConta, String data){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' AND dataTransacao LIKE '"+data+"%'"+" AND tipoTransacao='SAQUE'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}	
}
