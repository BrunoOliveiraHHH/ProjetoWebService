package com.projeto.web.service.rest.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.projeto.web.service.model.dto.UsuarioAuthDTO;
import com.projeto.web.service.model.excecao.AplicacaoException;
import com.projeto.web.service.model.servico.AuthUsuarioService;
import com.projeto.web.service.model.util.PasswordUtil;


public class AuthenticationProvider implements AuthenticationManager {
	
	@Autowired
	private AuthUsuarioService service;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		
		UsuarioAuthDTO usuarioAuth = (UsuarioAuthDTO) authentication.getPrincipal();
		
		UserDetails usuario = service.loadUserByUsername(usuarioAuth.getLogin());
			
		
		if(usuario != null) {
			
			if(usuario.getPassword() == null) {
				throw new AplicacaoException("Senha Invalida");
			}
			boolean isValidaSenha = PasswordUtil.encryptPassword(pwd).equals(usuario.getPassword());
			
			if (usuarioAuth.getLogin().equals(usuario.getUsername()) && isValidaSenha) {
				return new UsernamePasswordAuthenticationToken(username, pwd, usuario.getAuthorities());
			} else {

				if(!isValidaSenha) {
					throw new AplicacaoException("Senha Invalida");
				} else {
					throw new BadCredentialsException("Autenticação falhou!!!!");
				}
			}
		} else {
			throw new BadCredentialsException("Autenticação falhou!!!!");
		}
	}

	

}