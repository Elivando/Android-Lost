package org.androidLost.server.utils.service.beans.request;

import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;
import org.androidLost.server.utils.entities.TokenEntity;

public class HistoricoLocalizacaoRequest {
	TokenEntity tokenEntity;
	HistoricoLocalizacaoEntity historicoLocalizacaoEntity;
	
	public HistoricoLocalizacaoRequest(){
		super();
	}
	
	public HistoricoLocalizacaoRequest(TokenEntity tokenEntity, HistoricoLocalizacaoEntity historicoLocalizacaoEntity) {
		super();
		this.tokenEntity = tokenEntity;
		this.historicoLocalizacaoEntity = historicoLocalizacaoEntity;
	}
	
	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}
	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}
	public HistoricoLocalizacaoEntity getHistoricoLocalizacaoEntity() {
		return historicoLocalizacaoEntity;
	}
	public void setHistoricoLocalizacaoEntity(HistoricoLocalizacaoEntity historicoLocalizacaoEntity) {
		this.historicoLocalizacaoEntity = historicoLocalizacaoEntity;
	}
}
