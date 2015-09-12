package org.androidLost.server.service;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.androidLost.server.database.repositories.ITokenRepository;
import org.androidLost.server.database.repositories.IUsuarioRepository;
import org.androidLost.server.service.utils.AbstractGenericService;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.utils.service.beans.request.UsuarioEntityRequest;
import org.androidLost.server.utils.service.beans.response.TokenResponse;
import org.androidLost.server.utils.service.interfaces.ITokenService;

@Named
public class TokenService extends AbstractGenericService<TokenEntity, Long> implements ITokenService {

	@Inject
	private ITokenRepository tokenRepository;

	@Inject
	private IUsuarioRepository usuarioRepository;

	@Override
	protected JpaRepository<TokenEntity, Long> getGenericRepository() {
		return this.tokenRepository;
	}

	@Override
	public TokenResponse gerarToken(UsuarioEntityRequest usuarioEntityRequest) {
		TokenResponse Response;
		TokenEntity Token = new TokenEntity();

		UsuarioEntity usuarioEntity = usuarioEntityRequest.getUsuarioEntity();
		String plataforma = usuarioEntityRequest.getPlataforma();

		Calendar Data = Calendar.getInstance();
		Data.add(Calendar.MINUTE, 30);

		try {

			usuarioEntity = usuarioRepository.findByLoginAndSenha(usuarioEntity.getLogin(), usuarioRepository.pwdEncrypt(usuarioEntity.getSenha()));

			if (usuarioEntity == null) {
				Response = new TokenResponse("ERROR", "-2-Usuário ou Senha inválidos!", null);
			} else {
				Token = tokenRepository.findByToken(tokenRepository.newToken(usuarioEntity.getId(), plataforma));
				Token.setUsuarioEntity(usuarioEntity);

				Response = new TokenResponse("SUCCESS", "", Token);
			}
		} catch (Exception e) {
			Response = new TokenResponse("EXCEPTION", e.toString(), null);
		}

		return Response;
	}

	@Override
	public TokenResponse validaToken(TokenEntity tokenEntity) {
		TokenResponse Response;

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY, -2);

		String plataforma = tokenEntity.getPlataforma();

		try {

			TokenEntity DBToken = tokenRepository.findByToken(tokenEntity.getToken());

			if (DBToken == null) {
				Response = new TokenResponse("ERROR", "-1-Token inválido!", null);
			} else if (DBToken.getDataAtivacao().before(c.getTime())) {
				Response = new TokenResponse("ERROR", "-1-Token inválido!", null);
			} else {
				Response = new TokenResponse("SUCCESS", "", tokenRepository.findByToken(tokenRepository.newToken(DBToken.getUsuarioEntity().getId(), plataforma)));
			}

		} catch (Exception e) {
			Response = new TokenResponse("EXCEPTION", e.toString(), null);
		}

		return Response;
	}

	@Override
	public TokenResponse inativaToken(TokenEntity tokenEntity) {
		TokenResponse Response;
		try {
			tokenEntity = tokenRepository.findByToken(tokenEntity.getToken());
			if (tokenEntity != null) {
				tokenEntity.setStatus("C");
				tokenRepository.saveAndFlush(tokenEntity);
			}
			Response = new TokenResponse("SUCCESS", "", null);
		} catch (Exception e) {
			Response = new TokenResponse("EXCEPTION", e.toString(), null);
		}
		return Response;
	}

}
