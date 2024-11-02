package com.projeto.web.service.model.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="USUARIO")
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USU_SEQ")
	private Long id;
	
	@Column(name="USU_NOME")
	private String nome;
	
	@Column(name="USU_LOGIN")
	private String login;
	
	@Column(name="USU_SENHA")
	private String senha;
	
	@Column(name="USU_DT_CRIACAO")
	private LocalDateTime dtCriacao;
	
}
