package org.androidLost.server.utils.service.beans.response;

import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;

public class LocalizacaoResponse {
	private String status;
	private String mensagem;
	private HistoricoLocalizacaoEntity localizacao;
	
	public LocalizacaoResponse() {
		super();
	}

	public LocalizacaoResponse(String status, String mensagem, HistoricoLocalizacaoEntity localizacao) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.localizacao = localizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HistoricoLocalizacaoEntity getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(HistoricoLocalizacaoEntity localizacao) {
		this.localizacao = localizacao;
	}
	
}
