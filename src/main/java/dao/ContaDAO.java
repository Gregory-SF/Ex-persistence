package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Conta;

public class ContaDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
	
	public Conta inserir(Conta conta) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		em.close();
		return null;
	}
	
	public Conta alterar(Long idConta, Conta mesmaConta) {
		Conta contaBanco = null;
		if(idConta!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			contaBanco = buscarPorId(idConta);
			
			if(contaBanco.getId()!=null) {
				contaBanco.setTipoConta(mesmaConta.getTipoConta());
				contaBanco.setContaAtiva(mesmaConta.isContaAtiva());
				contaBanco.setNumeroConta(mesmaConta.getNumeroConta());
				contaBanco.setDataAlteracao(mesmaConta.getDataAlteracao());
				em.merge(contaBanco);
			}
			em.getTransaction().commit();
			em.close();
		}
		return contaBanco;
				
	}
	
	public void excluir(Long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Conta conta = buscarPorId(id);
		Conta contaMerge = em.merge(conta);
		if(conta != null) {
			em.remove(contaMerge);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public Conta buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Conta conta = em.find(Conta.class, id);
		em.close();
		return conta;		
	}
	
	public List<Conta> listarTodos(){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		List<Conta> contas = em.createQuery("from Conta").getResultList();
		em.close();
		return contas;
	}
	
//	public List<Conta> buscarPorCpf(String cpf){
//		EntityManager em = emf.createEntityManager();
//		//hql: hibernate query language
//		Query query = em.createQuery("from Conta where cpfCorrentista='"+cpf+"'");
//		List<Conta> movimentacaos = query.getResultList();
//		em.close();
//		return movimentacaos;
//	}
}
