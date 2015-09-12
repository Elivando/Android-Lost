package org.androidLost.server.test.database.repositories;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.androidLost.server.database.repositories.IAparelhoRepository;
import org.androidLost.server.test.database.AbstractDatabaseTest;
import org.androidLost.server.utils.entities.AparelhoEntity;

public class AparelhoRepositoryTest extends AbstractDatabaseTest {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Inject
	private IAparelhoRepository aparelhoRepository;

	
	@Test
	public void testAparelhoRepository() {
		List<AparelhoEntity> aparelhos = this.aparelhoRepository.findAll();

		this.logger.debug(aparelhos);
	}

}
