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
			switch (context.getExternalContext().getRequestParameterMap().get("entity")) {
			case "banda":
				final Object cod = context.getExternalContext().getRequestParameterMap().get("codBanda");
				if (cod != null) {
					final Integer codBanda = Integer.valueOf(cod.toString());
					final byte[] imagem = perfilservice.obterImagemBanda(codBanda);
					if (imagem != null) {
						return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
					}
				}
				return new DefaultStreamedContent();
			case "musico":
				final Object codMusico = context.getExternalContext().getRequestParameterMap().get("codMusico");
				if (codMusico != null) {
					final Integer codMu = Integer.valueOf(codMusico.toString());
					final byte[] imagem = perfilservice.obterImagemMusico(codMu);
					if (imagem != null) {
						return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
					}
				}
				return new DefaultStreamedContent();
			case "evento":
				final Object codEvento = context.getExternalContext().getRequestParameterMap().get("codEvento");
				if (codEvento != null) {
					final Integer codMu = Integer.valueOf(codEvento.toString());
					final byte[] imagem = perfilservice.obterImagemEvento(codMu);
					if (imagem != null) {
						return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
					}
				}
				return new DefaultStreamedContent();

			default:
				return new DefaultStreamedContent();
			}

		}
	}

}
