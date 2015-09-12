package org.androidLost.server.test.database.repositories;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.androidLost.server.database.repositories.ITokenRepository;
import org.androidLost.server.test.database.AbstractDatabaseTest;
import org.androidLost.server.utils.entities.TokenEntity;
import org.androidLost.server.utils.entities.UsuarioEntity;

public class TokenRepositoryTest extends AbstractDatabaseTest {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Inject
	private ITokenRepository tokenRepository;
	
	@Test
	public void testTokenRepository() {
		List<TokenEntity> tokens = this.tokenRepository.findAll();

		this.logger.debug(tokens);
	}

	@Test
	public void testTokenFind(){
		UsuarioEntity usr = new UsuarioEntity();
		
		usr.setId(1L);
		
		String token = "";
		token = tokenRepository.newToken(1L,"WEB");
		
		
		
		this.logger.debug(tokenRepository.findByToken(token));
		//this.logger.debug(token);
	}
	
	@Test
	public void testTokenInsert() {
		UsuarioEntity user = new UsuarioEntity();
		user.setId(1L);
		TokenEntity token = new TokenEntity();
		token.setStatus("A");
		token.setToken("testedetoken");
		token.setDataAtivacao(new Date());
		token.setUsuarioEntity(user);
		
		this.tokenRepository.saveAndFlush(token);
	}
}
