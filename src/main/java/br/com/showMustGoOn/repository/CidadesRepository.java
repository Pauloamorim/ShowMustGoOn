package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.com.showMustGoOn.model.Cidade;

public class CidadesRepository implements Serializable {

	private static final long serialVersionUID = -5654994095270773966L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Cidade> listarCidadesPorEstado(Integer codEstado, String cidade){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		criteria.createAlias("estado", "estado",JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("estado.codEstado", codEstado));
		criteria.add(Restrictions.ilike("nome", cidade,MatchMode.ANYWHERE));
		
		return criteria.list();
		
		
	}

}
