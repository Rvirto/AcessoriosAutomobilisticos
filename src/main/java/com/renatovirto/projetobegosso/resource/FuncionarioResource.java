package com.renatovirto.projetobegosso.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Funcionario;
import com.renatovirto.projetobegosso.repository.FuncionarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public List<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> adicionar(@Valid @RequestBody Funcionario funcionario) {
		
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
	}
	
	@GetMapping("/{id}")
	public Funcionario buscarPeloCodigo(@PathVariable Long id) throws ObjectNotFoundException {
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deletar(@PathVariable Long id) {
		funcionarioRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
