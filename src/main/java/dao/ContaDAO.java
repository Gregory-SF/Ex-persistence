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
	
	/**
	 * Função que retorna a quantidade de contas por cliente
	 * 
	 * @param Long id_cliente
	 * 
	 * @return integer
	 *  
	 * */
	public int verificarContasPorCliente(Long id){
		EntityManager em = emf.createEntityManager();
		//hql: hibernate query language
		Query query = em.createQuery("from Conta where id_cliente='"+id+"'");
		List<Conta> contas = query.getResultList();
		int quantidade  = contas.size();
		em.close();
		return quantidade;
	}
	
	public double buscarSaldoContaPoupanca(Long idConta, String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' and dataTransacao LIKE '"+data+"%'");
		double saldo = 0.;
		try {
			saldo =  Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldo;
	}
	
	public String BuscarTresMesesAntes(String data){
		EntityManager em = emf.createEntityManager();
		Query query = em.createNativeQuery("select subdate(adddate(last_day('"+data+"'), interval 1 day),interval 3 month)");
		String dataAntiga = query.getSingleResult().toString();
		em.close();
		return dataAntiga;
	}
	
	public double buscarCredito(Long idConta, String dataInicial, String dataFinal){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select sum(valorOperacao) from Movimentacao where id_conta='"+idConta+"' AND dataTransacao BETWEEN '"+dataInicial+" 00:00:00.000' AND '"+dataFinal+" 23:59:59.999'");
		double saldoMedio = 0.;
		try {
			saldoMedio = Double.parseDouble(query.getSingleResult().toString());
		} catch (Exception e) {}
		em.close();
		return saldoMedio/3;
	}
}
