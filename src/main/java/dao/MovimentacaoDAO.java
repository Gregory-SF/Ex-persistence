package dao;

//import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Movimentacao;

public class MovimentacaoDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
	
	public Movimentacao inserir(Movimentacao movimentacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(movimentacao);
		em.getTransaction().commit();
		em.close();
		return null;
	}
	
	public Movimentacao alterar(Movimentacao movimentacao) {
		Movimentacao novaMovimentacao = null;
		if(movimentacao.getId()!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			novaMovimentacao = buscarPorId(movimentacao.getId());
			
			if(novaMovimentacao.getId()!=null) {
				novaMovimentacao.setDescricao(movimentacao.getDescricao());
				em.merge(novaMovimentacao);
			}
			em.getTransaction().commit();
			em.close();
		}
		return novaMovimentacao;
	}
	
	public void excluir(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Movimentacao movimentacao = buscarPorId(id);
		Movimentacao movimentacaoMerge = em.merge(movimentacao);
		if(movimentacao != null) {
			em.remove(movimentacaoMerge);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movimentacao> listarTodos(){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		List<Movimentacao> movimentacaos = em.createQuery("from Movimentacao").getResultList();
		em.close();
		return movimentacaos;
	}
	// buscar todas as contas de acordo com o CPF
	public List<Movimentacao> buscarPorCpf(String cpf){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Movimentacao where cpfCorrentista='"+cpf+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	// buscar todas as contas de acordo com o tipo de transação
	public List<Movimentacao> buscarPorTransacao(String tipoTrsn){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Movimentacao where tipoTransacao='"+tipoTrsn+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public Movimentacao buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Movimentacao movimentacao = em.find(Movimentacao.class, id);
		em.close();
		return movimentacao;		
	}
	
	public List<Movimentacao> buscarPorCpfData(String cpf, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where cpfCorrentista='"+cpf+"' AND dataTransacao LIKE '"+data+"%'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorCpfAteData(String cpf, String dataInicial, String dataFinal){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where cpfCorrentista='"+cpf+"' AND dataTransacao BETWEEN '"+dataInicial+"' AND '"+dataFinal+" 23:59:59.999'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorCpfTransacao(String cpf, String tipoTrsn){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where cpfCorrentista='"+cpf+"'" +" AND tipoTransacao='"+tipoTrsn+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	
	public double buscarSaldo (String cpf){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where cpfCorrentista='"+cpf+"'");
		double saldo = 0.;
		try {
			saldo = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoSaque(String cpf, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where cpfCorrentista='"+cpf+"' AND dataTransacao LIKE '"+data+"%'"+" AND tipoTransacao='saque'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoTipo(String cpf, String tipo){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where cpfCorrentista='"+cpf+"' AND tipoTransacao ='"+tipo+"'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
}
