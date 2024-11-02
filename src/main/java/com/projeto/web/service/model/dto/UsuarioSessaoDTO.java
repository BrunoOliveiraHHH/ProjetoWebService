package com.projeto.web.service.model.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioSessaoDTO implements UserDetails{
	
	private static final long serialVersionUID = 2409596567718980605L;
	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities; 
	
	public UsuarioSessaoDTO(Long id, String userName, String password) {
		this.id = id;
		this.username = userName;
		this.password = password;
		//this.authorities = this.translate(null); 
	}
	
	/*
	private Collection<? extends GrantedAuthority> translate(List<PerfilPermissaoDTO> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(Mapper.ObjetoToJSON(roles)));
		return authorities; 
	} 
	*/
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return authorities; 
	} 
	
	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}