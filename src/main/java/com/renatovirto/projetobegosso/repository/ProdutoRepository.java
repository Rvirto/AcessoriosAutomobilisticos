package com.renatovirto.projetobegosso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetobegosso.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
