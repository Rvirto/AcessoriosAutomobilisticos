package com.renatovirto.projetobegosso.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Long id) throws ObjectNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	@PostMapping
	public ResponseEntity<Produto> adicionar(@Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
}
