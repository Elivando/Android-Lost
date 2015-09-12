package org.androidLost.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.androidLost.server.utils.entities.TokenEntity;

public interface ITokenRepository extends JpaRepository<TokenEntity, Long> {

	//Consulta de Token utilizando HQL
	@Query(value =
			"	SELECT "
			+ "		t "
			+ "	FROM "
			+ "		TokenEntity t "
			+ "	WHERE "
			+ "		t.status = 'A' "
			+ "		AND t.token = ?")
	public TokenEntity findByToken(String token);

	// Chamada de procedure utilizando query nativa do MySQL
	@Query(nativeQuery = true, value = "CALL gerarToken(?1,?2)")
	public String newToken(long codUsuario, String plataforma);
}
