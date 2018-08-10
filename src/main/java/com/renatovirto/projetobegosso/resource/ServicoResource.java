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

import com.renatovirto.projetobegosso.model.Servico;
import com.renatovirto.projetobegosso.repository.ServicoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@GetMapping
	public List<Servico> listar() {
		return servicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Servico buscarPeloId(@PathVariable Long id) throws ObjectNotFoundException {
		Optional<Servico> servico = servicoRepository.findById(id);
		
		return servico.orElseThrow(() -> new ObjectNotFoundException(
				"Serviço não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
	}
	
	@PostMapping
	public ResponseEntity<Servico> adicionar(@Valid @RequestBody Servico servico) {
		
		Servico servicoSalvo = servicoRepository.save(servico);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
	}
}
