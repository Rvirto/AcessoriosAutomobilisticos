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

import com.renatovirto.projetobegosso.model.Carro;
import com.renatovirto.projetobegosso.repository.CarroRepository;
import com.renatovirto.projetobegosso.service.CarroService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/carros")
public class CarroResource {

	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public List<Carro> listar() {
		return carroRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Carro buscarPorId(@PathVariable Long id) throws ObjectNotFoundException {
		return carroService.buscar(id);
	}
	
	@PostMapping
	public ResponseEntity<Carro> adicionar(@Valid @RequestBody Carro carro) {
		Carro carroSalvo = carroRepository.save(carro);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro> atualizar(@Valid @RequestBody Carro carro, @PathVariable Long id) throws ObjectNotFoundException {
		Carro carroSalvo = carroService.atualizarCarro(carro, id);
		
		return ResponseEntity.ok().body(carroSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		carroRepository.deleteById(id);
	}
}
