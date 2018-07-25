package com.philipe.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philipe.cursomc.domain.Cliente;

import com.philipe.cursomc.repository.ClienteRepository;
import com.philipe.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +id + ", Tipo "+ Cliente.class.getName()));
	}
}
