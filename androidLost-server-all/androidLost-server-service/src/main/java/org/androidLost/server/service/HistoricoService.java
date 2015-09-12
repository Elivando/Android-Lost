package org.androidLost.server.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.androidLost.server.database.repositories.IHistoricoLocalizacaoRepository;
import org.androidLost.server.service.utils.AbstractGenericService;
import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;
import org.androidLost.server.utils.service.interfaces.IHistoricoService;

@Named
public class HistoricoService extends AbstractGenericService<HistoricoLocalizacaoEntity, Long> implements IHistoricoService {

	@Inject
	private IHistoricoLocalizacaoRepository historicoRepository;

	@Override
	protected JpaRepository<HistoricoLocalizacaoEntity, Long> getGenericRepository() {
		return this.historicoRepository;
	}

}
