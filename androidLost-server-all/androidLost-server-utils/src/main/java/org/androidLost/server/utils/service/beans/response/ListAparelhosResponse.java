package org.androidLost.server.utils.service.beans.response;

import java.util.List;

import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;

public class ListAparelhosResponse {
	private String status;
	private String mensagem;
	private String token;
	private List<Usuario_AparelhoEntity> aparelhos;

	public ListAparelhosResponse() {
		super();
	}

	public ListAparelhosResponse(String status, String mensagem, String tokenEntity, List<Usuario_AparelhoEntity> aparelhos) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.token = tokenEntity;
		this.aparelhos = aparelhos;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Usuario_AparelhoEntity> getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(List<Usuario_AparelhoEntity> aparelhos) {
		this.aparelhos = aparelhos;
	}

}
