package com.philipe.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.philipe.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
