package com.renatovirto.projetobegosso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetobegosso.model.Imagem;
import com.renatovirto.projetobegosso.model.Produto;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

	public List<Imagem> findByProduto(Produto produto);
}
