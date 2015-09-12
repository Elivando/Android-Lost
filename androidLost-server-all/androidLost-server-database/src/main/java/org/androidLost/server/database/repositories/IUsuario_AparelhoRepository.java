package org.androidLost.server.database.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.androidLost.server.utils.entities.HistoricoLocalizacaoEntity;
import org.androidLost.server.utils.entities.Usuario_AparelhoEntity;

public interface IUsuario_AparelhoRepository extends
		JpaRepository<Usuario_AparelhoEntity, Long> {

	@Query("  SELECT "
			+ "		ua "
			+ "FROM "
			+ "		Usuario_AparelhoEntity ua "
			+ "		INNER JOIN FETCH ua.usuarioEntity u "
			+ "WHERE " 
			+ "		u.id = ?1 ")
	public List<Usuario_AparelhoEntity> findByCodUsuario(long CodUsuario);

	@Query("  SELECT "
			+ "		New List(h) "
			+ "FROM "
			+ "		HistoricoLocalizacaoEntity h "
			+ "		INNER JOIN h.usuario_AparelhoEntity ua "
			+ "  	INNER JOIN ua.usuarioEntity u "
			+ "		INNER JOIN ua.aparelhoEntity a "
			+ "WHERE " 
			+ "		u.id = ?1 "
			+ "		AND a.id = ?2"
			+ "		AND h.data BETWEEN ?3 AND ?4")
	public List<HistoricoLocalizacaoEntity> listHistoricoPorPeriodo(long usuario, long aparelho, Date inicio, Date fim);
	
	@Query(	"SELECT h2 FROM HistoricoLocalizacaoEntity h2 WHERE h2.id = ("
			+ "SELECT "
			+ "		MAX(h.id) "
			+ "FROM "
			+ "		HistoricoLocalizacaoEntity h "
			+ "		INNER JOIN h.usuario_AparelhoEntity ua "
			+ "  	INNER JOIN ua.usuarioEntity u "
			+ "		INNER JOIN ua.aparelhoEntity a "
			+ "WHERE " 
			+ "		u.id = ?1 "
			+ "		AND a.id = ?2 ) ")
	public HistoricoLocalizacaoEntity ultimaLocalizacao(long usuario, long aparelho);

	@Query("  SELECT "
			+ "		ua "
			+ "FROM "
			+ "		Usuario_AparelhoEntity ua "
			+ "  	INNER JOIN ua.usuarioEntity u "
			+ "		INNER JOIN ua.aparelhoEntity a "
			+ "WHERE " 
			+ "		u.id = ?1 "
			+ "		AND a.imei = ?2")
	public Usuario_AparelhoEntity findByUsuarioAndImei(long usuario, String IMEI);
	
}
