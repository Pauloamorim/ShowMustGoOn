package br.com.showMustGoOn.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
public  class BaseController implements Serializable {

	private static final long serialVersionUID = 5595397590073552735L;
	
	public String verificarLogado(){
		FacesContext fc = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		 
		 if(session.getAttribute("logado") == null || (Boolean)session.getAttribute("logado") == false){
			 return "/pages/index.xhtml?faces-redirect=true";
		 }
		 return null;
	}
	
	public void adicionarMensagem(String mensagem,Severity severity){
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(severity, mensagem, ""));
	}
	

}
