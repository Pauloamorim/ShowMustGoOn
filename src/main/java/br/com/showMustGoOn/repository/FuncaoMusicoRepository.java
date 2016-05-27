package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.model.Funcao;
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
		criarJoins(criteria);
		criarRestricoes(musicoDTO, criteria);
		setarProjecoes(criteria);
		
		return criteria.list();
	}

	private void setarProjecoes(Criteria criteria) {
		criteria.setProjection(Projections.property("musico"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}

	private void criarJoins(Criteria criteria) {
		criteria.createAlias("musico", "musico");
		criteria.createAlias("musico.estado", "estado");
		criteria.createAlias("musico.cidade", "cidade");
		criteria.createAlias("funcao", "funcao");
	}

	private void criarRestricoes(MusicoDTO musicoDTO, Criteria criteria) {
		if(StringUtils.isNotBlank(musicoDTO.getNome())){
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike("musico.nome", musicoDTO.getNome(),MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("musico.sobrenome", musicoDTO.getNome(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.or(disjunction));
		}
		if(musicoDTO.getEstado() != null){
			criteria.add(Restrictions.eq("estado.codEstado", musicoDTO.getEstado().getCodEstado()));
		}
		if(musicoDTO.getCidade() != null){
			criteria.add(Restrictions.eq("cidade.id", musicoDTO.getCidade().getId()));
		}
		if(musicoDTO.getSexo() != null){
			criteria.add(Restrictions.eq("musico.sexo", musicoDTO.getSexo()));
		}
		if(musicoDTO.getListaFuncoes() != null && !musicoDTO.getListaFuncoes().isEmpty()){
			criteria.add(Restrictions.in("funcao", musicoDTO.getListaFuncoes()));
		}
	}

	@SuppressWarnings(value="unchecked")
	public List<Funcao> carregarFuncoesMusico(Integer codMusico) {
		Criteria criteria = criarCriteriaCarregarFuncaoMusico(codMusico);
		criteria.setProjection(Projections.property("funcao"));
		return criteria.list();
	}
	@SuppressWarnings(value="unchecked")
	public List<FuncaoMusico> listarFuncoesMusico(Integer codMusico) {
		Criteria criteria = criarCriteriaCarregarFuncaoMusico(codMusico);
		return criteria.list();
	}

	private Criteria criarCriteriaCarregarFuncaoMusico(Integer codMusico) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FuncaoMusico.class);
		criteria.createAlias("musico", "musico");
		criteria.createAlias("funcao", "funcao");
		
		criteria.add(Restrictions.eq("musico.codMusico", codMusico));
		return criteria;
	}

	public void excluirTodos(List<FuncaoMusico> funcoesMusico) {
		for (FuncaoMusico funcao : funcoesMusico) {
			manager.remove(funcao);
		}
	}

	public void salvar(List<FuncaoMusico> criarListaFuncoesMusico) {
		for (FuncaoMusico funcaoMusico : criarListaFuncoesMusico) {
			manager.persist(funcaoMusico);
		}
	}

}
