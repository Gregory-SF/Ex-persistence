package servico;

import dao.AbstractDAO;

public interface BaseService<T> {
	AbstractDAO<T> getDAO();
	
	default T inserir(T entidade) {
		return getDAO().inserir(entidade);
	}
	T alterar(T entidade);
	default void excluir(Long id) {
		getDAO().excluir(id);
	};


}
