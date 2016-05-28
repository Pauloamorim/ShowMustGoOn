package br.com.showMustGoOn.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.showMustGoOn.service.perfilService;

@Named
@RequestScoped
public class ImagemController {

	@Inject
	private perfilService perfilservice;

	public StreamedContent getObterImagemBanda() throws IOException {
		final FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			final Object cod = context.getExternalContext().getRequestParameterMap().get("codBanda");
			if (cod != null) {
				final Integer codBanda = Integer.valueOf(cod.toString());
				final byte[] imagem = perfilservice.obterImagemBanda(codBanda);
				if (imagem != null) {
					return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
				}
			}
			return new DefaultStreamedContent();
		}
	}

}
