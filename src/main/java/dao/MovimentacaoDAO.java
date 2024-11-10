package dao;

//import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Movimentacao;
import entidade.TransacaoTipo;

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
	
	public Movimentacao alterar(Long idMovimentacao, Movimentacao movimentacao) {
		Movimentacao novaMovimentacao = null;
		if(idMovimentacao!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			novaMovimentacao = buscarPorId(idMovimentacao);
			
			if(novaMovimentacao.getId()!=null) {				
				novaMovimentacao.setDataTransacao(movimentacao.getDataTransacao());
				novaMovimentacao.setDescricao(movimentacao.getDescricao());
				novaMovimentacao.setTipoTransacao(movimentacao.getTipoTransacao());
				novaMovimentacao.setValor(movimentacao.getValor());
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
	
	 //buscar todas as movimentacões de acordo com o id da conta
	public List<Movimentacao> buscarPorIdConta(Long idConta){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}

	// buscar todas as contas de acordo com o CPF
//	public List<Movimentacao> buscarPorCpf(String cpf){
//		EntityManager em = emf.createEntityManager();
//		//hql: hibernate query language
//		Query query = em.createQuery("from Movimentacao where cpfCorrentista='"+cpf+"'");
//		List<Movimentacao> movimentacaos = query.getResultList();
//		em.close();
//		return movimentacaos;
//	}
	
	// buscar todas as contas de acordo com o tipo de transação
	public List<Movimentacao> buscarPorTransacao(TransacaoTipo tipoTrsn){
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
	
	public List<Movimentacao> buscarPorIdData(Long idConta, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"' AND dataTransacao LIKE '"+data+"%'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaAteData(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"' AND dataTransacao BETWEEN '"+dataInicial+"' AND '"+dataFinal+" 23:59:59.999'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	public List<Movimentacao> buscarPorIdContaTransacao(Long idConta, String tipoTrsn){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Movimentacao where id_conta='"+idConta+"'" +" AND tipoTransacao='"+tipoTrsn+"'");
		List<Movimentacao> movimentacaos = query.getResultList();
		em.close();
		return movimentacaos;
	}
	
	
	public double buscarSaldo (Long idConta){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"'");
		double saldo = 0.;
		try {
			saldo = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoSaque(Long idConta, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' AND dataTransacao LIKE '"+data+"%'"+" AND tipoTransacao='SAQUE'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoTipo(String cpf, TransacaoTipo tipo){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where Id_Conta='"+cpf+"' AND tipoTransacao ='"+tipo+"'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
}
