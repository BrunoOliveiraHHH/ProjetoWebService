package com.projeto.web.service.rest.autenticacao;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.projeto.web.service.model.dto.TokenDTO;
import com.projeto.web.service.model.dto.UsuarioAuthDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenServiceImpl implements TokenService {

	@Autowired
	private JwtConfiguration jwtConfiguration;

	/** Cria token principal requisição de autenticação */
	@Override
	public ResponseEntity<TokenDTO> createAccessToken(Authentication authentication, UsuarioAuthDTO usuario,
			String perfil) {

		String accessToken = null;
		String refreshToken = null;
		Date now = null;
		Date expiration = null;

		if (authentication != null) {

			String login = (String) authentication.getPrincipal();

			now = new Date();
			expiration = new Date(now.getTime() + jwtConfiguration.getJwtExpiration());

			accessToken = getAccessToken(login, now, expiration);
			refreshToken = getRefreshToken(login, now);
		}

		String perfil_bs64 = "";

		if (perfil != null) {
			if (!perfil.equals("[]")) {
				perfil_bs64 = this.criptografaBase64(perfil);
			}
		}

		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").created(now).expiration(expiration)
				.token(accessToken).refreshToken(refreshToken).usuario(usuario).perfil(perfil_bs64).build());
	}

	/** Valida token */
	@Override
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(jwtConfiguration.getJwtSecret()).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/** Get id do token */
	@Override
	public String getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(jwtConfiguration.getJwtSecret()).parseClaimsJws(token).getBody();
		return body.getSubject();
	}

	/** Cria toke, baseado no refresh token quando a sessão do usuário expirou */
	@Override
	public ResponseEntity<TokenDTO> refreshToken(String refreshToken, UsuarioAuthDTO usuario, String perfil) {

		if (refreshToken.contains("Bearer "))
			refreshToken = refreshToken.substring("Bearer ".length());

		String userName = null;

		try {
			Claims body = Jwts.parser().setSigningKey(jwtConfiguration.getJwtSecret()).parseClaimsJws(refreshToken)
					.getBody();

			userName = body.getSubject();
			// perfil = (String) body.get("perfil");

		} catch (ExpiredJwtException e) {
			// Indica que o refreshToken se encontra expirado
		}

		return createAccessTokenRefresh(userName, perfil, usuario);

	}

	/** Gera o Token */
	public String generateToken(Authentication authentication) {

		String login = (String) authentication.getPrincipal();
		// String perfil = obterPerfil(authentication);

		Date now = new Date();
		Date exp = new Date(now.getTime() + jwtConfiguration.getJwtExpiration());
		login = login.replace("-", "").replace(".", "");

		return Jwts.builder().setIssuer("IRS").setSubject(login).setIssuedAt(new Date()).claim("perfil", "")
				.setExpiration(exp).signWith(SignatureAlgorithm.HS256, jwtConfiguration.getJwtSecret()).compact();
	}

	/** Cria token de refresh requisição de autenticação */
	public ResponseEntity<TokenDTO> createAccessTokenRefresh(String login, String perfil, UsuarioAuthDTO usuario) {

		if (login != null) {
			Date now = new Date();
			Date expiration = new Date(now.getTime() + jwtConfiguration.getJwtExpiration());

			String accessToken = getAccessToken(login, now, expiration);
			String refreshToken = getRefreshToken(login, now);
			String perfil_bs64 = "";

			if (perfil != null) {
				if (!perfil.equals("[]")) {
					perfil_bs64 = this.criptografaBase64(perfil);
				}
			}

			return ResponseEntity.ok(TokenDTO.builder().type("Bearer").created(now).expiration(expiration)
					.token(accessToken).refreshToken(refreshToken).usuario(usuario).perfil(perfil_bs64).build());
		}

		return null;
	}

	/** Gera token principal */
	private String getAccessToken(String username, Date now, Date expiration) {

		return Jwts.builder().setIssuer("IRS").setSubject(username).setIssuedAt(now).claim("perfil", "")
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, jwtConfiguration.getJwtSecret())
				.compact();
	}

	/** Gera refresh token */
	private String getRefreshToken(String username, Date now) {

		Date expiration = new Date(now.getTime() + jwtConfiguration.getJwtExpiration() * 3);

		return Jwts.builder().setIssuer("IRS").setSubject(username).setIssuedAt(now).claim("perfil", "")
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS256, jwtConfiguration.getJwtSecret())
				.compact();
	}

	/**
	 * Get perfil vinculado no token private String obterPerfil(Authentication
	 * authentication) { String perfil = ""; if (authentication.getAuthorities() !=
	 * null && !authentication.getAuthorities().isEmpty()) { for (GrantedAuthority
	 * grant: authentication.getAuthorities()) { perfil = grant.getAuthority(); } }
	 * 
	 * return perfil; }
	 */

	/** Criptografa o string Base64 */
	private String criptografaBase64(String conteudo) {

		String conteudo_bs64 = Base64.getEncoder().encodeToString(conteudo.getBytes());

		StringBuffer buffer = new StringBuffer(conteudo_bs64);

		if (conteudo_bs64.length() > 5) {
			UUID random = UUID.randomUUID();
			String codigo = random.toString().substring(0, 8);

			codigo = Base64.getEncoder().encodeToString(codigo.getBytes());

			buffer.insert(2, codigo.substring(0, 6));
		}

		conteudo_bs64 = buffer.toString();

		return conteudo_bs64;
	}

	/**
	 * Descriptografa o string Base64 private String descriptografaBase64(String
	 * conteudo) {
	 * 
	 * String retorno = conteudo;
	 * 
	 * if(!retorno.substring(0, 1).equals("[")) {
	 * 
	 * StringBuffer buffer = new StringBuffer(retorno);
	 * 
	 * if(retorno.length() > 5) { buffer.replace(2,8,""); }
	 * 
	 * retorno = buffer.toString();
	 * 
	 * try {
	 * 
	 * byte[] decodedBytes = Base64.getDecoder().decode(retorno); retorno = new
	 * String(decodedBytes);
	 * 
	 * } catch (Exception e) { return retorno; } }
	 * 
	 * 
	 * return retorno; }
	 */

}
