package com.projeto.web.service.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.web.service.model.entidade.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>, CrudRepository<Usuario, Long>{

	@Query(value="SELECT * FROM USUARIO WHERE UPPER(USU_LOGIN) = UPPER(:login)",nativeQuery=true)
	Usuario findByLogin(@Param("login") String login);

}
