package org.androidLost.server.utils.service.beans.response;

import org.androidLost.server.utils.entities.TokenEntity;

public class TokenResponse {
	private String status;
	private String mensagem;
	private TokenEntity token;
	
	public TokenResponse(String status, String mensagem, TokenEntity token) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.token = token;
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
	public TokenEntity getToken() {
		return token;
	}
	public void setToken(TokenEntity token) {
		this.token = token;
	}
}
