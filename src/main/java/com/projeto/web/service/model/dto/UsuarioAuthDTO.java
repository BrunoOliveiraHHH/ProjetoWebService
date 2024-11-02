package com.projeto.web.service.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({ "id", "nome", "login"})
public class UsuarioAuthDTO {
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
}