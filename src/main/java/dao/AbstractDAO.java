package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	 * Para essa função funcionar, o id precisa ser da conta a ser alterada, mas as informações serão 
	 * as novas e o id o mesmo
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
			List<T> entidades = em.createQuery("from '"+nomeClasse()+"'").getResultList();
			return entidades;
		} finally {
			em.close();
		}
	}
	
	public T buscarPorId(Long id) {
		EntityManager em = getEntityManager();
		try {
			T classe = em.find(entityClass, id);
			return classe;		
		} finally {
			em.close();
		}
	}
	
	
	/**
	 * Função que retorna quatro meses meses antes, ou seja, últimos três meses excluindo o mês atual
	 * @param String data
	 * @return String data 3 meses antes
	 * */
	public String BuscarTresMesesAntes(String data){
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery("select subdate(adddate(last_day(:data),interval 1 day),interval 5 month)").setParameter("data", data);
		String dataAntiga = query.getSingleResult().toString();
		em.close();
		return dataAntiga;
	}
	
	public String BuscarUltimoDiaMesPassado(String data){
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery("select last_day(subdate(:data, interval 1 month))").setParameter("data", data);
		String dataAntiga = query.getSingleResult().toString();
		em.close();
		return dataAntiga;
	}
	
	private String nomeClasse() {
		return this.entityClass.getName();
	}
}


