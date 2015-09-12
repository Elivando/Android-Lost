package org.androidLost.server.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.androidLost.server.database.repositories.IAparelhoRepository;
import org.androidLost.server.service.utils.AbstractGenericService;
import org.androidLost.server.utils.entities.AparelhoEntity;
import org.androidLost.server.utils.service.interfaces.IAparelhoService;

@Named
public class AparelhoService extends AbstractGenericService<AparelhoEntity, Long> implements IAparelhoService {

	@Inject
	private IAparelhoRepository aparelhoRepository;

	@Override
	protected JpaRepository<AparelhoEntity, Long> getGenericRepository() {
		return this.aparelhoRepository;
	}

}
