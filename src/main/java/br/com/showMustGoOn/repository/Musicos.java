package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.model.Musico;

public class Musicos implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;
	
	@Inject
	private EntityManager manager;
	
	public Musico porId(Long id) {
		return manager.find(Musico.class, id);
	}
	
	public List<Musico> todas() {
		return manager.createQuery("from Musico", Musico.class).getResultList();
	}

	public Boolean verificarLogin(String email, String senha) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Musico.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", senha));
		
		criteria.setProjection(Projections.rowCount());
		
		
		return (Long)criteria.uniqueResult() > 0 ? true : false;
	}

	public Musico obterMusicoPorEmail(String email, Integer codMusico) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Musico.class);
		criteria.add(Restrictions.eq("email", email));
		if(codMusico != null){
			criteria.add(Restrictions.ne("codMusico", codMusico));
		}
		return (Musico)criteria.uniqueResult();
		
	}

	public void salvar(Musico musico) {
		manager.merge(musico);
	}

	@SuppressWarnings("unchecked")
	public List<Musico> listarMusicosPorNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Musico.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.ilike("nome", nome,MatchMode.ANYWHERE));
		disjunction.add(Restrictions.ilike("sobrenome", nome,MatchMode.ANYWHERE));
		criteria.add(disjunction);
		return criteria.list();
	}

}
