package com.philipe.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philipe.cursomc.domain.Categoria;
import com.philipe.cursomc.repository.CategoriaRepository;
import com.philipe.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +id + ", Tipo "+ Categoria.class.getName()));
	}
}
