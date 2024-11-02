package com.projeto.web.service.rest.autenticacao;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.projeto.web.service.model.dto.TokenDTO;
import com.projeto.web.service.model.dto.UsuarioAuthDTO;

@Service
public interface TokenService {

	public ResponseEntity<TokenDTO> createAccessToken(Authentication authentication, UsuarioAuthDTO usuario, String perfil);

	public ResponseEntity<TokenDTO> refreshToken(String refreshToken, UsuarioAuthDTO usuario, String perfil);
	
	public boolean isTokenValid(String token);

	public String getTokenId(String token);
}
