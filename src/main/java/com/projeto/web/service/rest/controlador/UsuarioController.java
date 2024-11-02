package com.projeto.web.service.rest.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.web.service.model.dto.UsuarioDTO;
import com.projeto.web.service.model.servico.UsuarioService;
import com.projeto.web.service.model.util.MediaType;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping(value = "/consultaPorId", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<UsuarioDTO> consultaPorId(@RequestBody UsuarioDTO dtoInput) {
		return new ResponseEntity<UsuarioDTO>(this.service.findById(dtoInput), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<UsuarioDTO> create(@PathParam(value = "id") Long id) {
		UsuarioDTO dtoInput = new UsuarioDTO();
		dtoInput.setId(id);
		return new ResponseEntity<UsuarioDTO>(this.service.findById(dtoInput), HttpStatus.OK);
	}
}
