package br.com.showMustGoOn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.showMustGoOn.DTO.MusicoFuncaoDTO;
import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.CadastroBandaService;

@Named
@ViewScoped
public class CadastroBandaController extends BaseController implements Serializable {

	private static final long serialVersionUID = 9101388687393676224L;
	private List<Estado> listaEstados;

	@Inject
	private CadastroBandaService cadastroBandaService;

	private Banda banda;
	private Musico musicoVinculo;
	private List<Funcao> listaFuncoes;
	private List<Funcao> funcoesSelecionadasVinculo;
	private List<MusicoFuncaoDTO> listaMusicosBanda;
	private UploadedFile imagemBanda;

	@PostConstruct
	public void init(){
		setListaEstados(cadastroBandaService.listarEstados());
		setBanda(new Banda());
		setListaFuncoes(cadastroBandaService.listarFuncoes());
		setListaMusicosBanda(new ArrayList<MusicoFuncaoDTO>());
	}

	public List<Cidade> listarCidades(String cidade){
		return cadastroBandaService.listarCidadesPorEstado(getBanda().getEstado().getCodEstado(),cidade);
	}
	public List<Musico> listarMusico(String nome){
		return cadastroBandaService.listarMusicosPorNome(nome);
	}
	public void adicionarMusico(){
		try {
			validarMusicoVinculado();
			getListaMusicosBanda().add(new MusicoFuncaoDTO(getMusicoVinculo(), getFuncoesSelecionadasVinculo()));
			setMusicoVinculo(null);
			setFuncoesSelecionadasVinculo(new ArrayList<Funcao>());
		} catch (final Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public void excluirMusico(){
		final Integer codMusico =Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codMusico"));

		for (final Iterator iterator = getListaMusicosBanda().iterator(); iterator.hasNext();) {
			final MusicoFuncaoDTO dto = (MusicoFuncaoDTO) iterator.next();
			if(dto.getMusico().getCodMusico().equals(codMusico)){
				iterator.remove();
			}
		}
	}
	public String salvarBanda(){
		try {
			cadastroBandaService.salvarBanda(getBanda(),getListaMusicosBanda());
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("msgSucessoBanda", "Sua Banda foi cadastrada. :D");
			return "perfil.xhmtl?faces-redirect=true";
		} catch (final Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}

	private void validarMusicoVinculado() throws Exception {
		for (final MusicoFuncaoDTO dto : getListaMusicosBanda()) {
			if(dto.getMusico().getCodMusico().equals(getMusicoVinculo().getCodMusico())){
				throw new Exception("Este músico já está vinculado a banda.");
			}
		}
	}

	public void fileUpload(FileUploadEvent event) throws IOException {
		try {
			getBanda().setImagem(event.getFile().getContents());
			setImagemBanda(event.getFile());

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

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public Musico getMusicoVinculo() {
		return musicoVinculo;
	}

	public void setMusicoVinculo(Musico musicoVinculo) {
		this.musicoVinculo = musicoVinculo;
	}

	public List<Funcao> getListaFuncoes() {
		return listaFuncoes;
	}

	public void setListaFuncoes(List<Funcao> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
	}

	public List<Funcao> getFuncoesSelecionadasVinculo() {
		return funcoesSelecionadasVinculo;
	}

	public void setFuncoesSelecionadasVinculo(List<Funcao> funcoesSelecionadasVinculo) {
		this.funcoesSelecionadasVinculo = funcoesSelecionadasVinculo;
	}

	public List<MusicoFuncaoDTO> getListaMusicosBanda() {
		return listaMusicosBanda;
	}

	public void setListaMusicosBanda(List<MusicoFuncaoDTO> listaMusicosBanda) {
		this.listaMusicosBanda = listaMusicosBanda;
	}

	public UploadedFile getImagemBanda() {
		return imagemBanda;
	}

	public void setImagemBanda(UploadedFile imagemBanda) {
		this.imagemBanda = imagemBanda;
	}




}
