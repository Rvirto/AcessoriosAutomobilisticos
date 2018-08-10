package com.renatovirto.projetobegosso.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Fornecedor;
import com.renatovirto.projetobegosso.repository.FornecedorRepository;
import com.renatovirto.projetobegosso.service.FornecedorService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Fornecedor buscarPorId(@PathVariable Long id) throws ObjectNotFoundException {
		return fornecedorService.buscar(id);
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> adicionar(@Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(@Valid @RequestBody Fornecedor fornecedor, @PathVariable Long id) throws ObjectNotFoundException {
		Fornecedor fornecedorSalvo = fornecedorService.atualizarFornecedor(fornecedor, id);
		
		return ResponseEntity.ok().body(fornecedorSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		fornecedorRepository.deleteById(id);
	}
}
