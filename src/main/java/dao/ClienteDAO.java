package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Cliente;

public class ClienteDAO {
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
	
	public Cliente inserir(Cliente cliente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
		return null;
	}
	
	public Cliente alterar(Cliente cliente) {
		Cliente clienteNovo = null;
		if(cliente.getId()!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			clienteNovo = buscarPorId(cliente.getId());
			
			if(clienteNovo.getId()!=null) {
				//clienteNovo.setDescricao(cliente.getDescricao());
				em.merge(clienteNovo);
			}
			em.getTransaction().commit();
			em.close();
		}
		return clienteNovo;
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
	
	public List<Conta> listarTodos(){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		List<Conta> contas = em.createQuery("from Conta").getResultList();
		em.close();
		return contas;
	}
	
	
}
