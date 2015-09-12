package org.androidLost.server.service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.androidLost.server.database.repositories.ITokenRepository;
import org.androidLost.server.database.repositories.IUsuarioRepository;
import org.androidLost.server.service.utils.AbstractGenericService;
import org.androidLost.server.utils.returnObject;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;
import org.androidLost.server.utils.service.beans.response.TokenResponse;
import org.androidLost.server.utils.service.interfaces.IUsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class UsuarioService extends AbstractGenericService<UsuarioEntity, Long> implements IUsuarioService {

	@Inject
	private IUsuarioRepository usuarioRepository;

	@Inject
	private ITokenRepository tokenRepository;

	@Override
	protected JpaRepository<UsuarioEntity, Long> getGenericRepository() {
		return this.usuarioRepository;
	}

	@Override
	public TokenResponse cadastraUsuario(UsuarioEntity usuario) {
		TokenResponse retorno;
		try {
			if (usuario.getCpf() != null && usuario.getEmail() != null && usuario.getLogin() != null && usuario.getNome() != null && usuario.getSenha() != null) {
				if (usuarioRepository.findByCpf(usuario.getCpf()) == null) {
					if (usuarioRepository.findByLogin(usuario.getLogin()) == null) {
						TokenEntity token = new TokenEntity();

						usuario.setStatus("A");
						usuario.setSenha(usuarioRepository.pwdEncrypt(usuario.getSenha()));
						usuario.setDataCadastro(new Date());
						usuario.setDataAltSenha(null);

						token.setUsuarioEntity(usuarioRepository.saveAndFlush(usuario));
						token.setToken(tokenRepository.newToken(token.getUsuarioEntity().getId(), "WEB"));

						retorno = new TokenResponse("SUCCESS", null, token);
					} else {
						retorno = new TokenResponse("ERROR", "Login não disponível.", null);
					}
				} else {
					retorno = new TokenResponse("ERROR", "CPF já cadastrado.", null);
				}
			} else {
				retorno = new TokenResponse("ERROR", "Dados de cadastro incompletos.", null);
			}
		} catch (Exception e) {
			retorno = new TokenResponse("EXCEPTION", e.toString(), null);
		}

		return retorno;
	}

	@Override
	public TokenEntity atualizaUsuario(TokenEntity Token) {
		TokenEntity retorno = null;
		try {
			TokenEntity DBToken = tokenRepository.findByToken(Token.getToken());
			if (DBToken != null) {
				UsuarioEntity usuarioEntity = Token.getUsuarioEntity();
				UsuarioEntity dbUsuarioEntity = usuarioRepository.findOne(usuarioEntity.getId());

				dbUsuarioEntity.setCpf(usuarioEntity.getCpf());
				dbUsuarioEntity.setEmail(usuarioEntity.getEmail());
				dbUsuarioEntity.setNome(usuarioEntity.getNome());

				retorno = new TokenEntity();
				retorno.setToken(DBToken.getToken());
				retorno.setUsuarioEntity(usuarioRepository.saveAndFlush(dbUsuarioEntity));
			}
		} catch (Exception e) {
		}

		return retorno;
	}

	@Override
	public returnObject inativaUsuario(TokenEntity Token) {
		returnObject Retorno = new returnObject();
		try {
			TokenEntity DBToken = tokenRepository.findByToken(Token.getToken());
			if (DBToken != null) {
				Retorno.setToken(DBToken.getToken());
				Token.getUsuarioEntity().setStatus("C");
				Retorno.setRetorno(usuarioRepository.saveAndFlush(Token.getUsuarioEntity()));
				Retorno.setStatus("SUCCESS");
			} else {
				Retorno.setRetorno("-1--1-Token inválido!");
				Retorno.setStatus("ERROR");
			}
		} catch (Exception e) {
			Retorno.setRetorno(e.toString());
			Retorno.setStatus("EXCEPTION");
		}

		return Retorno;
	}
}
