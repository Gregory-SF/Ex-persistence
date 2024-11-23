package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO<T> {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
	private final Class<T> entityClass;	
	
	public AbstractDAO(Class<T> classe) {
		this.entityClass = classe;
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public T inserir(T object) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(object);
			em.getTransaction().commit();
			em.close();
			return object;
		} finally {
			em.close();
		}
	}
	
	/*
	 * Para essa função funcionar, o id precisa ser da conta a ser alterada, mas as informações serão as novas
	 * */
	public T alterar(T object) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(object);
			em.getTransaction().commit();
			return object;
		} finally {
			em.close();
		}
	}
	
	public void excluir(Long id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T objectMerge = buscarPorId(id);
		em.remove(objectMerge);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<T> listarTodos(){
		EntityManager em = getEntityManager();
		try {
			List<T> clientes = em.createQuery("from '"+nomeClasse()+"'").getResultList();
			return clientes;
		} finally {
			em.close();
		}
	}
	
	public T buscarPorId(Long id) {
		EntityManager em = getEntityManager();
		try {
			T cliente = em.find(entityClass, id);
			return cliente;		
		} finally {
			em.close();
		}
	}
	
	private String nomeClasse() {
		return this.entityClass.getName();
	}
}


