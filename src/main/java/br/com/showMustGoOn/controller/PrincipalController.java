package br.com.showMustGoOn.controller;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class PrincipalController extends BaseController implements Serializable {

	private static final long serialVersionUID = -7882097701144799286L;
	
	public String logoff(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		
		session.removeAttribute("logado");
		return "index.xhtml?faces-redirect=true";
	}

}
