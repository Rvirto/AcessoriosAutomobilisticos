package com.renatovirto.projetobegosso.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Servico;
import com.renatovirto.projetobegosso.repository.ServicoRepository;
import com.renatovirto.projetobegosso.service.ServicoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<Servico> listar() {
		return servicoRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public Servico buscarPeloId(@PathVariable Long id) throws ObjectNotFoundException {
		return servicoService.buscar(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<Servico> adicionar(@Valid @RequestBody Servico servico) {
		
		Servico servicoSalvo = servicoRepository.save(servico);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Servico> atualizar(@Valid @RequestBody Servico servico, @PathVariable Long id) throws ObjectNotFoundException {
		Servico servicoSalvo = servicoService.atualizarServico(servico, id);
		
		return ResponseEntity.ok().body(servicoSalvo);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		servicoRepository.deleteById(id);
	}
}
