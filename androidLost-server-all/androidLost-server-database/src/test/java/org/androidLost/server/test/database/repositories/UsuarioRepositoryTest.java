package org.androidLost.server.test.database.repositories;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.androidLost.server.database.repositories.IUsuarioRepository;
import org.androidLost.server.test.database.AbstractDatabaseTest;
import org.androidLost.server.utils.entities.UsuarioEntity;

public class UsuarioRepositoryTest extends AbstractDatabaseTest{

	private final Logger logger = Logger.getLogger(getClass());

	@Inject
	private IUsuarioRepository usersrepository;

	@Test
	public void testUsusarioRepository() {
		List<UsuarioEntity> users = this.usersrepository.findAll();
		this.logger.debug(users);
	}

	@Test
	public void AxaLogin() {
		UsuarioEntity Logins = this.usersrepository.findByLogin("zezineustaquio");
		this.logger.debug(Logins);
	}
	
	@Test
	public void testepornome(){
		List<UsuarioEntity> nomes = this.usersrepository.findByNome("%Eustaquio%");
		this.logger.debug(nomes);
	}

	@Test
	public void testUserInsert() {

		UsuarioEntity user = new UsuarioEntity();
		Date data = new Date();
		user.setCpf("094.489.236-19");
		user.setDataCadastro(data);
		user.setStatus("A");
		user.setEmail("zezineustaquio@gmail.com");
		user.setNome("Jose Eustaquio");
		user.setSenha(usersrepository.pwdEncrypt("123"));
		user.setLogin("zezineustaquio");
		
		this.usersrepository.saveAndFlush(user);
	}
	
}
