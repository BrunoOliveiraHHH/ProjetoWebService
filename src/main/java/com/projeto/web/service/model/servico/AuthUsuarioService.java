package com.projeto.web.service.model.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.web.service.model.dto.UsuarioDTO;
import com.projeto.web.service.model.dto.UsuarioSessaoDTO;

@Service
public class AuthUsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioDTO usuario = this.service.findByLogin(username.replace(".", "").replace("-", ""));

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não possui autorização para acessar esse sistema");
		}

		String password = usuario.getSenha();

		return new UsuarioSessaoDTO(usuario.getId(), username, password);
	}

}
