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
	
	public Cliente alterar(Long idCliente, Cliente mesmoCliente) {
		Cliente clienteNovo = null;
		if(idCliente!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			clienteNovo = buscarPorId(idCliente);
			
			if(clienteNovo.getId()!=null) {
				clienteNovo.setCpfCliente(mesmoCliente.getCpfCliente());
				clienteNovo.setEmail(mesmoCliente.getEmail());
				clienteNovo.setNomeCliente(mesmoCliente.getNomeCliente());
				clienteNovo.setRgCliente(mesmoCliente.getRgCliente());
				clienteNovo.setTelefone(mesmoCliente.getTelefone());
				clienteNovo.setDataAlteracao(mesmoCliente.getDataAlteracao());
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
		Cliente cliente = buscarPorId(id);
		Cliente clienteMerge = em.merge(cliente);
		if(cliente != null) {
			em.remove(clienteMerge);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Cliente> listarTodos(){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		List<Cliente> clientes = em.createQuery("from Cliente").getResultList();
		em.close();
		return clientes;
	}
	
	public Cliente buscarPorCpf(String cpf){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Cliente where cpfCliente ='"+cpf+"'");
		Cliente cliente = (Cliente) query.getSingleResult();
		em.close();
		return cliente;
	}
	
	public Cliente buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Cliente cliente = em.find(Cliente.class, id);
		em.close();
		return cliente;		
	}
}
