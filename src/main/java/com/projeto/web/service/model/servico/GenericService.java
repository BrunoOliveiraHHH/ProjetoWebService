package com.projeto.web.service.model.servico;

import java.util.List;

public interface GenericService<D> {
	
	D create (D dado);
	
	D update (D dado);
	
	D findById (D dado);
	
	D deleteById (D dado);
	
	List<D> findAll();

}
