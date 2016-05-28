package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.showMustGoOn.DTO.BandaDTO;
import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.BandaMusicoFuncao;

public class BandaMusicoFuncaoRepository implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;

	@Inject
	private EntityManager manager;

	public BandaMusicoFuncao porId(Long id) {
		return manager.find(BandaMusicoFuncao.class, id);
	}

	public void salvar(BandaMusicoFuncao bandaMusicoFuncao) {
		manager.merge(bandaMusicoFuncao);
	}

	public void salvar(List<BandaMusicoFuncao> listBMF) {
		for (final BandaMusicoFuncao bandaMusicoFuncao : listBMF) {
			salvar(bandaMusicoFuncao);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Banda> listarBandasMusico(Integer codMusico) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(BandaMusicoFuncao.class);
		criteria.createAlias("musico", "musico");
		criteria.createAlias("banda", "banda");
		criteria.add(Restrictions.eq("musico.codMusico", codMusico));
		criteria.setProjection(Projections.property("banda"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<BandaMusicoFuncao> listarPorCodBanda(Integer codBanda) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(BandaMusicoFuncao.class);
		criteria.createAlias("banda", "banda");
		criteria.add(Restrictions.eq("banda.codBanda", codBanda));
		return criteria.list();
	}

	public void removeAll(List<BandaMusicoFuncao> lista) {
		for (final BandaMusicoFuncao bandaMusicoFuncao : lista) {
			manager.remove(bandaMusicoFuncao);
		}
	}

	public List<Banda> pesquisarBandas(BandaDTO dto) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(BandaMusicoFuncao.class);
		criteria.createAlias("banda", "banda");
		criteria.createAlias("musico", "musico");
		criteria.createAlias("banda.estado", "estado");
		criteria.createAlias("banda.cidade", "cidade");

		if (StringUtils.isNotBlank(dto.getNome())) {
			criteria.add(Restrictions.like("banda.nome", dto.getNome(), MatchMode.ANYWHERE));
		}
		if (dto.getMusico() != null) {
			criteria.add(Restrictions.eq("musico.codMusico", dto.getMusico().getCodMusico()));
		}
		if (dto.getEstado() != null) {
			criteria.add(Restrictions.eq("estado.codEstado", dto.getEstado().getCodEstado()));
		}
		if (dto.getCidade() != null) {
			criteria.add(Restrictions.eq("cidade.id", dto.getCidade().getId()));
		}

		criteria.setProjection(Projections.property("banda"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

}
