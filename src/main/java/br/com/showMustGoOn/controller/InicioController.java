package br.com.showMustGoOn.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class InicioController implements Serializable {

	private static final long serialVersionUID = 9101388687393676224L;
	private String login;
	private String senha;
	
	public void efetuarLogin(){
		FacesContext fc = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		 
		 RequestContext context = RequestContext.getCurrentInstance();
		 context.addCallbackParam("logado", true);
		 
	     session.setAttribute("logado", true);
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
	
	

}
