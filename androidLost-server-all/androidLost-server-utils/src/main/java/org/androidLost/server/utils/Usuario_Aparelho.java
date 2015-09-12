package org.androidLost.server.utils;

import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;

public class Usuario_Aparelho {
	private Usuario_AparelhoEntity usuario_AparelhoEntity;
	private TokenEntity tokenEntity;
	
	public Usuario_AparelhoEntity getUsuario_AparelhoEntity() {
		return usuario_AparelhoEntity;
	}
	public void setUsuario_AparelhoEntity(
			Usuario_AparelhoEntity usuario_AparelhoEntity) {
		this.usuario_AparelhoEntity = usuario_AparelhoEntity;
	}
	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}
	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}
	
}
