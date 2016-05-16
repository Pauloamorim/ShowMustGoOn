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

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.model.FuncaoMusico;
import br.com.showMustGoOn.model.Musico;

public class FuncaoMusicoRepository implements Serializable {

	private static final long serialVersionUID = 6610976612253146622L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Musico> listarMusicos(MusicoDTO musicoDTO) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FuncaoMusico.class);
		
		criteria.createAlias("musico", "musico");
		criteria.createAlias("musico.estado", "estado");
		criteria.createAlias("musico.cidade", "cidade");
		criteria.createAlias("funcao", "funcao");
		
		if(StringUtils.isNotBlank(musicoDTO.getNome())){
			criteria.add(Restrictions.ilike("musico.nome", musicoDTO.getNome(),MatchMode.ANYWHERE));
		}
		if(musicoDTO.getEstado() != null){
			criteria.add(Restrictions.eq("estado.codEstado", musicoDTO.getEstado().getCodEstado()));
		}
		if(musicoDTO.getCidade() != null){
			criteria.add(Restrictions.eq("cidade.id", musicoDTO.getCidade().getId()));
		}
		
		
		
		criteria.setProjection(Projections.property("musico"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return criteria.list();
	}

}
