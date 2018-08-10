package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto buscar(Long id) throws ObjectNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Produto atualizarProduto(Produto produto, Long id) throws ObjectNotFoundException {
		Produto produtoBuscado = buscar(id);
		BeanUtils.copyProperties(produto, produtoBuscado, "id");
		return produtoRepository.save(produtoBuscado);
	}
}
