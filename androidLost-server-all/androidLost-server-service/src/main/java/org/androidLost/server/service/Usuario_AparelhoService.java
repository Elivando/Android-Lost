package org.androidLost.server.service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.androidLost.server.database.repositories.IAparelhoRepository;
import org.androidLost.server.database.repositories.IHistoricoLocalizacaoRepository;
import org.androidLost.server.database.repositories.ITokenRepository;
import org.androidLost.server.database.repositories.IUsuarioRepository;
import org.androidLost.server.database.repositories.IUsuario_AparelhoRepository;
import org.androidLost.server.service.utils.AbstractGenericService;
import org.androidLost.server.utils.returnObject;
import org.androidLost.server.utils.entities.AparelhoEntity;
import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;
import org.androidLost.server.utils.service.beans.request.Usuario_AparelhoRequest;
import org.androidLost.server.utils.service.beans.request.HistoricoLocalizacaoRequest;
import org.androidLost.server.utils.service.beans.response.AparelhoResponse;
import org.androidLost.server.utils.service.beans.response.ListAparelhosResponse;
import org.androidLost.server.utils.service.beans.response.LocalizacaoResponse;
import org.androidLost.server.utils.service.beans.response.TokenResponse;
import org.androidLost.server.utils.service.beans.response.Usuario_AparelhoResponse;
import org.androidLost.server.utils.service.interfaces.IUsuario_AparelhoService;

@Named
public class Usuario_AparelhoService extends AbstractGenericService<Usuario_AparelhoEntity, Long> implements IUsuario_AparelhoService {

	@Inject
	private IUsuario_AparelhoRepository usu_apRepository;

	@Inject
	private ITokenRepository tokenRepository;

	@Inject
	private IHistoricoLocalizacaoRepository historicoLocalizacaoRepository;

	@Inject
	private IAparelhoRepository aparelhoRepository;

	@Inject
	private IUsuarioRepository usuarioRepository;

	@Override
	protected JpaRepository<Usuario_AparelhoEntity, Long> getGenericRepository() {
		return this.usu_apRepository;
	}

	@Override
	public Usuario_AparelhoResponse retornarAparelho(Usuario_AparelhoRequest usuario_AparelhoRequest) {
		Usuario_AparelhoResponse Retorno;
		
		long usuario = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getUsuarioEntity().getId();
		String IMEI = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getAparelhoEntity().getImei();
		String token = usuario_AparelhoRequest.getTokenEntity().getToken();
		
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Usuario_AparelhoEntity usuario_AparelhoEntity = usu_apRepository.findByUsuarioAndImei(usuario, IMEI);
				if (usuario_AparelhoEntity != null) {
					Retorno = new Usuario_AparelhoResponse("SUCCESS", "", usuario_AparelhoEntity);
				} else {
					Retorno = new Usuario_AparelhoResponse("ERROR", "-4-Usuario ou aparelho não encontrado!", null);
				}
			} else {
				Retorno = new Usuario_AparelhoResponse("ERROR", "-1-Token inválido!", null);
			}
		} catch (Exception e) {
			Retorno = new Usuario_AparelhoResponse("EXCEPTION", e.toString(), null);
		}

		return Retorno;
	}

	@Override
	public ListAparelhosResponse listarAparelhos(TokenEntity tokenEntity) {
		ListAparelhosResponse Retorno = new ListAparelhosResponse();
		
		long usuario = tokenEntity.getUsuarioEntity().getId();
		String token = tokenEntity.getToken();
		
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Retorno.setToken(DBToken.getToken());
				Retorno.setAparelhos(usu_apRepository.findByCodUsuario(usuario));
				Retorno.setStatus("SUCCESS");
			} else {
				Retorno.setMensagem("-1-Token inválido!");
				Retorno.setStatus("ERROR");
			}
		} catch (Exception e) {
			Retorno.setMensagem(e.toString());
			Retorno.setStatus("EXCEPTION");
		}

		return Retorno;
	}

	@Override
	public returnObject listarHistorico(long usuario, long aparelho, Date inicio, Date fim, String token) {
		returnObject Retorno = new returnObject();
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Retorno.setToken(DBToken.getToken());
				Retorno.setRetorno(usu_apRepository.listHistoricoPorPeriodo(usuario, aparelho, inicio, fim));
				Retorno.setStatus("SUCCESS");
			} else {
				Retorno.setRetorno("-1-Token inválido!");
				Retorno.setStatus("ERROR");
			}
		} catch (Exception e) {
			Retorno.setRetorno(e.toString());
			Retorno.setStatus("EXCEPTION");
		}

		return Retorno;
	}
	
	@Override
	public LocalizacaoResponse ultimaLocalizacao(Usuario_AparelhoRequest usuario_AparelhoRequest){
		LocalizacaoResponse Retorno = new LocalizacaoResponse();
		
		String token = usuario_AparelhoRequest.getTokenEntity().getToken();
		long usuario = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getUsuarioEntity().getId();
		long aparelho = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getAparelhoEntity().getId();
		
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Retorno.setLocalizacao(usu_apRepository.ultimaLocalizacao(usuario, aparelho));
				Retorno.setStatus("SUCCESS");
			} else {
				Retorno.setMensagem("-1-Token inválido!");
				Retorno.setStatus("ERROR");
			}
		} catch (Exception e) {
			Retorno.setMensagem(e.toString());
			Retorno.setStatus("EXCEPTION");
		}
		return Retorno;
	}

	@Override
	public AparelhoResponse inserirAparelho(Usuario_AparelhoRequest usuario_AparelhoRequest) {
		AparelhoResponse Response;
		try {
			
			String token = usuario_AparelhoRequest.getTokenEntity().getToken();
			long codUsuario = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getUsuarioEntity().getId();
			AparelhoEntity aparelhoEntity = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getAparelhoEntity();
			Usuario_AparelhoEntity usuario_AparelhoEntity = usuario_AparelhoRequest.getUsuario_AparelhoEntity();
			
			TokenEntity DBToken = tokenRepository.findByToken(token);
			
			if (DBToken != null) {
				UsuarioEntity usuarioEntity = usuarioRepository.findById(codUsuario);
				
				if (usuarioEntity != null) {
					usuario_AparelhoEntity.setAparelhoEntity(aparelhoRepository.saveAndFlush(aparelhoEntity));
					usuario_AparelhoEntity.setStatus('A');
					usuario_AparelhoEntity.setData(new Date());
					
					Response = new AparelhoResponse("SUCCESS", "", usu_apRepository.saveAndFlush(usuario_AparelhoEntity).getAparelhoEntity());
				} else {
					Response = new AparelhoResponse("ERROR", "-3-Usuário não encontrado!", null);
				}
			} else {
				Response = new AparelhoResponse("ERROR", "-1-Token inválido!", null);
			}
		} catch (Exception e) {
			Response = new AparelhoResponse("EXCEPTION", e.toString(), null);
		}
		return Response;
	}

	@Override
	public TokenResponse inserirHistorico(HistoricoLocalizacaoRequest inserirHistoricoRequest) {
		TokenResponse Response;
		try {
			
			HistoricoLocalizacaoEntity historicoLocalizacaoEntity = inserirHistoricoRequest.getHistoricoLocalizacaoEntity();
			String token = inserirHistoricoRequest.getTokenEntity().getToken();
			String imei = inserirHistoricoRequest.getHistoricoLocalizacaoEntity().getUsuario_AparelhoEntity().getAparelhoEntity().getImei();
			long codUsuario = inserirHistoricoRequest.getHistoricoLocalizacaoEntity().getUsuario_AparelhoEntity().getUsuarioEntity().getId(); 
			String plataforma =inserirHistoricoRequest.getTokenEntity().getPlataforma(); 
			
			TokenEntity DBToken = tokenRepository.findByToken(token);
			
			if (DBToken != null) {
				Usuario_AparelhoEntity usuario_AparelhoEntity = usu_apRepository.findByUsuarioAndImei(codUsuario, imei);
				if (usuario_AparelhoEntity != null) {
					historicoLocalizacaoEntity.setUsuario_AparelhoEntity(usuario_AparelhoEntity);
					historicoLocalizacaoRepository.saveAndFlush(historicoLocalizacaoEntity);
					Response = new TokenResponse("SUCCESS", "", tokenRepository.findByToken(tokenRepository.newToken(codUsuario,plataforma)));
				} else {
					Response = new TokenResponse("ERROR", "-4-Usuario ou aparelho não encontrado!", null);
				}
			} else {
				Response = new TokenResponse("ERROR", "-1-Token inválido!", null);
			}
		} catch (Exception e) {
			Response = new TokenResponse("EXCEPTION", e.toString(), null);
		}

		return Response;
	}

	@Override
	public returnObject inativarAparelho(long usuario, String IMEI, String token) {
		returnObject Retorno = new returnObject();
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Usuario_AparelhoEntity usuario_AparelhoEntity = usu_apRepository.findByUsuarioAndImei(usuario, IMEI);
				if (usuario_AparelhoEntity != null) {
					usuario_AparelhoEntity.setStatus('C');

					usu_apRepository.saveAndFlush(usuario_AparelhoEntity);
					Retorno.setRetorno("Aparelho desvinculado com sucesso!");
					Retorno.setStatus("SUCCESS");
				} else {
					Retorno.setRetorno("-4-Usuario ou aparelho não encontrado!");
					Retorno.setStatus("ERROR");
				}
				Retorno.setToken(DBToken.getToken());
			} else {
				Retorno.setRetorno("-1-Token inválido!");
				Retorno.setStatus("ERROR");
			}
		} catch (Exception e) {
			Retorno.setRetorno(e.toString());
			Retorno.setStatus("EXCEPTION");
		}

		return Retorno;
	}
	
	public Usuario_AparelhoResponse retornarConfiguracoes(Usuario_AparelhoRequest usuario_AparelhoRequest){
		Usuario_AparelhoResponse Retorno;
		
		long usuario = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getUsuarioEntity().getId();
		String token = usuario_AparelhoRequest.getTokenEntity().getToken();
		String IMEI = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getAparelhoEntity().getImei();
		
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Usuario_AparelhoEntity dbUsuario_AparelhoEntity = usu_apRepository.findByUsuarioAndImei(usuario, IMEI);
				if (dbUsuario_AparelhoEntity != null) {					
					Retorno = new Usuario_AparelhoResponse("SUCCESS", "", dbUsuario_AparelhoEntity);
				} else {
					Retorno = new Usuario_AparelhoResponse("ERROR", "-4-Usuario ou aparelho não encontrado!", null);
				}
			} else {
				Retorno = new Usuario_AparelhoResponse("ERROR", "-1-Token inválido!", null);
			}
		} catch (Exception e) {
			Retorno = new Usuario_AparelhoResponse("EXCEPTION", e.toString(), null);
		}

		return Retorno;
	}
	
	public Usuario_AparelhoResponse atualizaConfiguracoes(Usuario_AparelhoRequest usuario_AparelhoRequest){
		Usuario_AparelhoResponse Retorno;
		
		long usuario = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getUsuarioEntity().getId();
		String token = usuario_AparelhoRequest.getTokenEntity().getToken();
		String IMEI = usuario_AparelhoRequest.getUsuario_AparelhoEntity().getAparelhoEntity().getImei();
		Usuario_AparelhoEntity usuario_AparelhoEntity = usuario_AparelhoRequest.getUsuario_AparelhoEntity();
		
		char autoStart = usuario_AparelhoEntity.getAutoStart();
		char habilita3g = usuario_AparelhoEntity.getHabilita3G();
		char habilitaGPS = usuario_AparelhoEntity.getHabilitaGPS();
		char habilitaWifi = usuario_AparelhoEntity.getHabilitaWifi();
		char modFurto = usuario_AparelhoEntity.getModFurto();
		int syncTime = usuario_AparelhoEntity.getSyncTime();
		
		try {
			TokenEntity DBToken = tokenRepository.findByToken(token);
			if (DBToken != null) {
				Usuario_AparelhoEntity dbUsuario_AparelhoEntity = usu_apRepository.findByUsuarioAndImei(usuario, IMEI);
				if (dbUsuario_AparelhoEntity != null) {
					dbUsuario_AparelhoEntity.setAutoStart(autoStart);
					dbUsuario_AparelhoEntity.setHabilita3G(habilita3g);
					dbUsuario_AparelhoEntity.setHabilitaGPS(habilitaGPS);
					dbUsuario_AparelhoEntity.setHabilitaWifi(habilitaWifi);
					dbUsuario_AparelhoEntity.setModFurto(modFurto);
					dbUsuario_AparelhoEntity.setSyncTime(syncTime);
					
					Retorno = new Usuario_AparelhoResponse("SUCCESS", "", usu_apRepository.saveAndFlush(dbUsuario_AparelhoEntity));
				} else {
					Retorno = new Usuario_AparelhoResponse("ERROR", "-4-Usuario ou aparelho não encontrado!", null);
				}
			} else {
				Retorno = new Usuario_AparelhoResponse("ERROR", "-1-Token inválido!", null);
			}
		} catch (Exception e) {
			Retorno = new Usuario_AparelhoResponse("EXCEPTION", e.toString(), null);
		}

		return Retorno;
	}

}
