package com.projeto.web.service.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String dataCriacao;
}
