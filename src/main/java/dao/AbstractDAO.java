//package dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//
//public abstract class AbstractDAO {
//	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
//	
//	public <T> T inserir(T object) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(object);
//		em.getTransaction().commit();
//		em.close();
//		return object;
//	}
//}
//	
//	public <T> T alterar(Long idCliente, T mesmoCliente) {
//		T clienteNovo = null;
//		if(idCliente!=null) {
//			EntityManager em = emf.createEntityManager();
//			em.getTransaction().begin();
//			
//			clienteNovo = buscarPorId(idCliente);
//			
//			if(clienteNovo.getId()!=null) { //teoricamente, como eu já vou fornecer um id q exista no param, então não precisaria desse if. DEPOIS REVER
//				clienteNovo.setCpfCliente(mesmoCliente.getCpfCliente());
//				clienteNovo.setEmail(mesmoCliente.getEmail());
//				clienteNovo.setNomeCliente(mesmoCliente.getNomeCliente());
//				clienteNovo.setRgCliente(mesmoCliente.getRgCliente());
//				clienteNovo.setTelefone(mesmoCliente.getTelefone());
//				clienteNovo.setDataAlteracao(mesmoCliente.getDataAlteracao());
//				em.merge(clienteNovo);
//			}
//			em.getTransaction().commit();
//			em.close();
//		}
//		return clienteNovo;
//	}
//	
//	public void excluir(Long id) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		T cliente = buscarPorId(id);
//		T clienteMerge = em.merge(cliente);
//		if(cliente != null) {
//			em.remove(clienteMerge);
//		}
//		em.getTransaction().commit();
//		em.close();
//	}
//	
//	public List<T> listarTodos(){
//		EntityManager em = emf.createEntityManager();
//		//hql: hibernate query language
//		List<T> clientes = em.createQuery("from '"+T+"'").getResultList();
//		em.close();
//		return clientes;
//	}
//	
//	public Cliente buscarPorCpf(String cpf){
//		EntityManager em = emf.createEntityManager();
//		//hql: hibernate query language
//		Query query = em.createQuery("from Cliente where cpfCliente ='"+cpf+"'");
//		Cliente cliente = (Cliente) query.getSingleResult();
//		em.close();
//		return cliente;
//	}
//	
//	public <T> T buscarPorId(Long id, T obj) {
//		EntityManager em = emf.createEntityManager();
//		T cliente = em.find(T.class, id);
//		em.close();
//		return cliente;		
//	}
//}
//
//}
