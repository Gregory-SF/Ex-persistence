package dao;

//import java.util.Date;
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
	
	public Conta alterar(Conta conta) {
		Conta contaBanco = null;
		if(conta.getId()!=null) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			contaBanco = buscarPorId(conta.getId());
			
			if(contaBanco.getId()!=null) {
				contaBanco.setDescricao(conta.getDescricao());
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
	
	public List<Conta> listarTodos(){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		List<Conta> contas = em.createQuery("from Conta").getResultList();
		em.close();
		return contas;
	}
	// buscar todas as contas de acordo com o CPF
	public List<Conta> buscarPorCpf(String cpf){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Conta where cpfCorrentista='"+cpf+"'");
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}
	
	// buscar todas as contas de acordo com o tipo de transação
	public List<Conta> buscarPorTransacao(String tipoTrsn){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Conta where tipoTransacao='"+tipoTrsn+"'");
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}
	
	public Conta buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		Conta conta = em.find(Conta.class, id);
		em.close();
		return conta;		
	}
	
	public List<Conta> buscarPorCpfData(String cpf, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Conta where cpfCorrentista='"+cpf+"' AND dataTransacao LIKE '"+data+"%'");
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}
	
	public List<Conta> buscarPorCpfAteData(String cpf, String dataInicial, String dataFinal){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Conta where cpfCorrentista='"+cpf+"' AND dataTransacao BETWEEN '"+dataInicial+"' AND '"+dataFinal+" 23:59:59.999'");
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}
	
	public List<Conta> buscarPorCpfTransacao(String cpf, String tipoTrsn){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Conta where cpfCorrentista='"+cpf+"'" +" AND tipoTransacao='"+tipoTrsn+"'");
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}
	
	
	public double buscarSaldo (String cpf){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Conta where cpfCorrentista='"+cpf+"'");
		double saldo = 0.;
		try {
			saldo = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoSaque(String cpf, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Conta where cpfCorrentista='"+cpf+"' AND dataTransacao LIKE '"+data+"%'"+" AND tipoTransacao='saque'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public double buscarSaldoTipo(String cpf, String tipo){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Conta where cpfCorrentista='"+cpf+"' AND tipoTransacao ='"+tipo+"'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
}
