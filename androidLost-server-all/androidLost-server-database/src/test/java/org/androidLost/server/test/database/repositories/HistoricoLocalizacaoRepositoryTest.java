package org.androidLost.server.test.database.repositories;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.androidLost.server.database.repositories.IHistoricoLocalizacaoRepository;
import org.androidLost.server.test.database.AbstractDatabaseTest;
import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;

public class HistoricoLocalizacaoRepositoryTest extends AbstractDatabaseTest {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Inject
	private IHistoricoLocalizacaoRepository HistoricoLocalizacaoRepository;

	@Test
	public void testOrderDetailRepository() {
		List<HistoricoLocalizacaoEntity> historico = this.HistoricoLocalizacaoRepository.findAll();

		this.logger.debug(historico);
	}

}
