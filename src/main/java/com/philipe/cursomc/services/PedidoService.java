package com.philipe.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philipe.cursomc.domain.Pedido;
import com.philipe.cursomc.repository.PedidoRepository;
import com.philipe.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +id + ", Tipo "+ Pedido.class.getName()));
	}
}
