package br.com.showMustGoOn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Evento;
import br.com.showMustGoOn.service.CadastroEventoService;

@Named
@ViewScoped
public class CadastroEventoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 9101388687393676224L;

	@Inject
	CadastroEventoService cadastroEventoService;

	private List<Estado> listaEstados;
	private Evento evento;
	private UploadedFile imagemEvento;

	@PostConstruct
	public void init() {
		final String cod = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codEvento");

		if (StringUtils.isNotBlank(cod)) {
			setEvento(cadastroEventoService.obterEvento(Integer.valueOf(cod)));
		} else {
			setEvento(new Evento());
		}

		setListaEstados(cadastroEventoService.listarEstados());
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		getEvento().setMusicoResponsavel(
				cadastroEventoService.obterMusicoPorEmail((String) session.getAttribute("emailLogado")));
	}

	public List<Cidade> listarCidades(String cidade) {
		return cadastroEventoService.listarCidadesPorEstado(getEvento().getEstado().getCodEstado(), cidade);
	}

	public String salvar() {
		try {
			final String mensagemRetorno = getEvento().getId() != null ? "Seu Evento foi Alterado :D"
					: "Seu Evento foi cadastrado. :D";
			cadastroEventoService.salvarEvento(getEvento());
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("msgSucessoBanda", mensagemRetorno);
			return "perfil.xhmtl?faces-redirect=true";
		} catch (final Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}

	public void fileUpload(FileUploadEvent event) throws IOException {
		try {
			getEvento().setImagemEvento(event.getFile().getContents());
			setImagemEvento(event.getFile());

		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public UploadedFile getImagemEvento() {
		return imagemEvento;
	}

	public void setImagemEvento(UploadedFile imagemEvento) {
		this.imagemEvento = imagemEvento;
	}

}
