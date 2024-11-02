package com.projeto.web.service.rest.autenticacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwt.properties")
public class JwtConfiguration {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private Long jwtExpiration;

	@Value("${jwt.header}")
	private String jwtHeader;

	@Value("${jwt.issuer}")
	private String jwtIssuer;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public Long getJwtExpiration() {
		return jwtExpiration;
	}

	public String getJwtHeader() {
		return jwtHeader;
	}

	public String getJwtIssuer() {
		return jwtIssuer;
	}

}

