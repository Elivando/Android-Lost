package org.androidLost.server.test.database.repositories;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.androidLost.server.database.repositories.IUsuario_AparelhoRepository;
import org.androidLost.server.test.database.AbstractDatabaseTest;

public class Usuario_AparelhoRepositoryTest extends AbstractDatabaseTest {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Inject
	private IUsuario_AparelhoRepository Usuario_Aparelho;
	
	@Test
	public void testUsuario_AparelhoRepository() {
		this.logger.debug(this.Usuario_Aparelho.findByCodUsuario(1L));
	}
	
	@Test
	public void testUsuario_AparelhoRepositoryList() {
		this.logger.debug(this.Usuario_Aparelho.ultimaLocalizacao(1L, 2L));
	}
	
	@Test
	public void testUsuario_AparelhoRepositoryfind() {
		this.logger.debug(this.Usuario_Aparelho.findByUsuarioAndImei(1L, "a3s"));
	}
	
	@Test
	public void find() {
		this.logger.debug(this.Usuario_Aparelho.findAll());
	}
}
