package org.androidLost.server.utils.service.beans.request;

import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;

public class Usuario_AparelhoRequest {
	private TokenEntity tokenEntity;
	private Usuario_AparelhoEntity usuario_AparelhoEntity;
		
	public Usuario_AparelhoRequest() {
		super();
	}

	public Usuario_AparelhoRequest(TokenEntity tokenEntity, Usuario_AparelhoEntity usuario_AparelhoEntity) {
		super();
		this.tokenEntity = tokenEntity;
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
	}
	
	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}
	
	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}
	
	public Usuario_AparelhoEntity getUsuario_AparelhoEntity() {
		return usuario_AparelhoEntity;
	}
	
	public void setUsuario_AparelhoEntity(Usuario_AparelhoEntity usuario_AparelhoEntity) {
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
	}
	
}
