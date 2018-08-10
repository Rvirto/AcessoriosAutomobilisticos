package com.renatovirto.projetobegosso.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;
import com.renatovirto.projetobegosso.service.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Long id) throws ObjectNotFoundException {
		return produtoService.buscar(id);
	}
	
	@PostMapping
	public ResponseEntity<Produto> adicionar(@Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id) throws ObjectNotFoundException {
		Produto produtoSalvo = produtoService.atualizarProduto(produto, id);
		
		return ResponseEntity.ok().body(produtoSalvo);
	}
}
