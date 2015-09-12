package org.androidLost.server.utils.service.beans.request;

import org.androidLost.server.utils.entities.UsuarioEntity;

public class UsuarioEntityRequest {
	private String plataforma;
	private UsuarioEntity usuarioEntity;

	public UsuarioEntityRequest(String plataforma, UsuarioEntity usuarioEntity) {
		super();
		this.plataforma = plataforma;
		this.usuarioEntity = usuarioEntity;
	}

	public UsuarioEntityRequest() {
		super();
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}
