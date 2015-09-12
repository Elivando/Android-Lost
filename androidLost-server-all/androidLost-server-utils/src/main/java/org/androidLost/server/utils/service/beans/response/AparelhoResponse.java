package org.androidLost.server.utils.service.beans.response;

import org.androidLost.server.utils.entities.AparelhoEntity;

public class AparelhoResponse {
	private String status;
	private String mensagem;
	private AparelhoEntity aparelhoEntity;
	
	public AparelhoResponse() {
		super();
	}

	public AparelhoResponse(String status, String mensagem, AparelhoEntity aparelhoEntity) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.aparelhoEntity = aparelhoEntity;
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
	
	public AparelhoEntity getAparelhoEntity() {
		return aparelhoEntity;
	}
	
	public void setAparelhoEntity(AparelhoEntity aparelhoEntity) {
		this.aparelhoEntity = aparelhoEntity;
	}
	
}
