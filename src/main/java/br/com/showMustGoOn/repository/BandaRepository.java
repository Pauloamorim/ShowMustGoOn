package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.showMustGoOn.model.Banda;

public class BandaRepository implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;

	@Inject
	private EntityManager manager;

	public Banda porId(Integer id) {
		return manager.find(Banda.class, id);
	}

	public List<Banda> todas() {
		return manager.createQuery("from Banda", Banda.class).getResultList();
	}

	public void salvar(Banda banda) {
		manager.persist(banda);
		manager.flush();
	}

	public Banda obterBandaPorNome(String nome, Integer codBanda) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Banda.class);
		criteria.add(Restrictions.eq("nome", nome));
		if (codBanda != null) {
			criteria.add(Restrictions.ne("codBanda", codBanda));
		}
		return (Banda) criteria.uniqueResult();
	}

	public byte[] obterImagemBanda(Integer codBanda) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Banda.class);
		criteria.add(Restrictions.eq("codBanda", codBanda));
		criteria.setProjection(Projections.property("imagem"));
		return (byte[]) criteria.uniqueResult();
	}

	public void remove(Banda banda) {
		manager.remove(banda);
	}

	public void alterar(Banda banda) {
		manager.merge(banda);
	}

}
