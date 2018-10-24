package com.renatovirto.projetobegosso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Cliente;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	public Carrinho findByClienteAndStatus(Cliente cliente, String status);
}
