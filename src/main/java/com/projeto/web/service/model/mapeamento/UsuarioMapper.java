package com.projeto.web.service.model.mapeamento;

import org.springframework.stereotype.Service;

import com.projeto.web.service.model.dto.UsuarioDTO;
import com.projeto.web.service.model.entidade.Usuario;

@Service
public class UsuarioMapper {

	Usuario entity;

	UsuarioDTO dto;

	public Usuario toEntity(UsuarioDTO dado) {
		entity = new Usuario();

		if (dado != null) {
			entity.setId(dado.getId());
			entity.setLogin(dado.getLogin());
			entity.setNome(dado.getNome());
			entity.setSenha(dado.getSenha());
		}

		return entity;
	}

	public UsuarioDTO toDTO(Usuario dado) {
		dto = new UsuarioDTO();

		if (dado != null) {
			dto.setId(dado.getId());
			dto.setLogin(dado.getLogin());
			dto.setNome(dado.getNome());
			dto.setSenha(dado.getSenha());
		}

		return dto;
	}

}
