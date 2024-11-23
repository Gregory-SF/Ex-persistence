package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente> {
			
	public ClienteDAO() {
		super(Cliente.class);
	}
	
	//verificar com o prof se eu posso tratar os erros do dao no própio dao
	public Cliente buscarPorCpf(String cpf){
		EntityManager em = getEntityManager();
//		try {
			Query query = em.createQuery("from Cliente where cpfCliente ='"+cpf+"'");
			Cliente cliente = (Cliente) query.getSingleResult();
			em.close();
			return cliente;			
//		} catch (Exception e) {
//			throw new Error("Não existe cliente com esse cpf");
//		}
	}
	
}
