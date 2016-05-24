package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.enums.SexoEnum;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.InicioService;

@Named
@ViewScoped
public class InicioController implements Serializable {

	private static final long serialVersionUID = 9101388687393676224L;
	private String login;
	private String senha;
	private Musico musico = new Musico();
	private SexoEnum[] listaSexo = SexoEnum.values();
	private List<Estado> listaEstados;
	
	@Inject
	InicioService inicioService;
	
	@PostConstruct
	public void init(){
		setListaEstados(inicioService.listarEstados());
	}
	
	public void efetuarLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		 
		 RequestContext context = RequestContext.getCurrentInstance();
		 
		 if(inicioService.verificarLogin(getLogin(), getSenha())){
			 context.addCallbackParam("logado", true);
			 session.setAttribute("logado", true);
		 }else{
			 context.addCallbackParam("logado", false);
			 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ou Senha incorretos.", ""));
		 }
	}
	
	public List<Cidade> listarCidades(String cidade){
		return inicioService.listarCidadesPorEstado(getMusico().getEstado().getCodEstado(),cidade);
	}
	
	public void salvar(){
		
	}
	
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Musico getMusico() {
		return musico;
	}

	public void setMusico(Musico musico) {
		this.musico = musico;
	}

	public SexoEnum[] getListaSexo() {
		return listaSexo;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	
	

}
