package com.renatovirto.projetobegosso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	public List<Orcamento> findByCliente(Cliente cliente);
}
