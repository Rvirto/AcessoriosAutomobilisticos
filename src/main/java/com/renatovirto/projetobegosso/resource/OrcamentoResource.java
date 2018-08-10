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

import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.repository.OrcamentoRepository;
import com.renatovirto.projetobegosso.service.OrcamentoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoResource {

	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private OrcamentoService orcamentoService;
	
	@GetMapping
	public List<Orcamento> listar() {
		return orcamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Orcamento buscarPeloId(@PathVariable Long id) throws ObjectNotFoundException {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(id);
		
		return orcamento.orElseThrow(() -> new ObjectNotFoundException(
				"Orcamento não encontrado! Id: " + id + ", Tipo: " + ObjectNotFoundException.class.getName()));
	}
	
	@PostMapping
	public ResponseEntity<Orcamento> adicionar(@Valid @RequestBody Orcamento orcamento) throws ObjectNotFoundException {
		Orcamento orcamentoSalvo = orcamentoRepository.save(
				orcamentoService.calcularTotalProduto(orcamento));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoSalvo);
	}
	
}
