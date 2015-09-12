package org.androidLost.server.database.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.androidLost.server.utils.entities.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	@Query("	SELECT "
			+ "		u "
			+ "	FROM "
			+ "		UsuarioEntity u "
			+ "	WHERE "
			+ "		u.status = 'A' "
			+ "		AND u.login = ?1 ")
	public UsuarioEntity findByLogin(String login);

	public UsuarioEntity findById(Long id);

	@Query("	SELECT "
			+ "		u "
			+ "	FROM "
			+ "		UsuarioEntity u "
			+ "	WHERE "
			+ "		u.status = 'A' "
			+ "		AND u.login = ?1 "
			+ "		AND u.senha = ?2 ")
	public UsuarioEntity findByLoginAndSenha(String login, String senha);

	@Query("	SELECT "
			+ "		u "
			+ "	FROM "
			+ "		UsuarioEntity u "
			+ "	WHERE "
			+ "		u.status = 'A' "
			+ "		AND u.nome LIKE ?1 ")
	public List<UsuarioEntity> findByNome(String Nome);
	
	public UsuarioEntity findByCpf(String cpf);

	@Query(nativeQuery = true, value = "SELECT md5(?1);")
	public String pwdEncrypt(String Senha);

}
