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

import com.renatovirto.projetobegosso.model.Fornecedor;
import com.renatovirto.projetobegosso.repository.FornecedorRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Fornecedor buscarPorId(@PathVariable Long id) throws ObjectNotFoundException {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		
		return fornecedor.orElseThrow(() -> new ObjectNotFoundException(
				"Fornecedor não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> adicionar(@Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
}
