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

import br.com.showMustGoOn.DTO.EventosDTO;
import br.com.showMustGoOn.model.Evento;

public class EventoRepository implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;

	@Inject
	private EntityManager manager;

	public Evento porId(Integer id) {
		return manager.find(Evento.class, id);
	}

	public void salvar(Evento evento) {
		manager.persist(evento);
		manager.flush();
	}

	public byte[] obterImagemBanda(Integer id) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Evento.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setProjection(Projections.property("imagemEvento"));
		return (byte[]) criteria.uniqueResult();
	}

	public void remove(Evento evento) {
		manager.remove(evento);
	}

	public void alterar(Evento evento) {
		manager.merge(evento);
	}

	@SuppressWarnings("unchecked")
	public List<Evento> listarEventosPorMusico(Integer codMusico) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Evento.class);
		criteria.createAlias("musicoResponsavel", "musico");
		criteria.add(Restrictions.eq("musico.codMusico", codMusico));
		return criteria.list();
	}

	public List<Evento> pesquisar(EventosDTO eventosDTO) {
		final Session session = manager.unwrap(Session.class);
		final Criteria criteria = session.createCriteria(Evento.class);
		if (StringUtils.isNotBlank(eventosDTO.getNome())) {
			criteria.add(Restrictions.ilike("nome", eventosDTO.getNome(), MatchMode.ANYWHERE));
		}
		if (eventosDTO.getData() != null) {
			criteria.add(Restrictions.eq("data", eventosDTO.getData()));
		}
		if (eventosDTO.getHoraInicio() != null) {
			criteria.add(Restrictions.eq("horaInicio", eventosDTO.getHoraInicio()));
		}
		if (eventosDTO.getHoraFim() != null) {
			criteria.add(Restrictions.eq("horaFim", eventosDTO.getHoraFim()));
		}
		if (StringUtils.isNotBlank(eventosDTO.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", eventosDTO.getDescricao(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(eventosDTO.getEndereco())) {
			criteria.add(Restrictions.ilike("endereco", eventosDTO.getEndereco(), MatchMode.ANYWHERE));
		}
		return criteria.list();

	}

}
