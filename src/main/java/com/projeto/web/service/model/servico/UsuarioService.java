package com.projeto.web.service.model.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.web.service.model.dto.UsuarioDTO;
import com.projeto.web.service.model.entidade.Usuario;
import com.projeto.web.service.model.excecao.AplicacaoException;
import com.projeto.web.service.model.mapeamento.UsuarioMapper;
import com.projeto.web.service.model.repositorio.UsuarioRepository;

@Service
public class UsuarioService implements GenericService<UsuarioDTO>{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper mapper;

	@Override
	public UsuarioDTO create(UsuarioDTO dado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO update(UsuarioDTO dado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO findById(UsuarioDTO dado) {
		Optional<Usuario> entity = this.repository.findById(dado.getId());
		
		if(entity.isEmpty()) {
			throw new AplicacaoException("Usuario não existe !");
		}
		
		return this.mapper.toDTO(entity.get());
	}

	@Override
	public UsuarioDTO deleteById(UsuarioDTO dado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public UsuarioDTO findByLogin(String login) {
		Usuario usuario = repository.findByLogin(login);
		
		if(usuario == null) {
			
		}
		
		return this.mapper.toDTO(usuario);
	}

}
