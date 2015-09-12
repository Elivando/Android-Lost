package org.androidLost.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;

public interface IHistoricoLocalizacaoRepository extends JpaRepository<HistoricoLocalizacaoEntity, Long> {

}
