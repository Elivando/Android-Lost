package org.androidLost.server.utils.service.beans.response;

import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;

public class Usuario_AparelhoResponse {
	private String status;
	private String mensagem;
	private Usuario_AparelhoEntity usuario_AparelhoEntity;
	
	public Usuario_AparelhoResponse() {
		super();
	}
	
	public Usuario_AparelhoResponse(String status, String mensagem, Usuario_AparelhoEntity usuario_AparelhoEntity) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
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
	
	public Usuario_AparelhoEntity getUsuario_AparelhoEntity() {
		return usuario_AparelhoEntity;
	}
	
	public void setUsuario_AparelhoEntity(Usuario_AparelhoEntity usuario_AparelhoEntity) {
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
	}
	
}
