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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;
import com.renatovirto.projetobegosso.service.CarrinhoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoResource {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<Carrinho> listarCarrinho() {
		return carrinhoRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<Carrinho> adicionar(@Valid @RequestBody Carrinho carrinho) {
		carrinhoRepository.save(carrinho);
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Carrinho> atualizar(@Valid @RequestBody Carrinho carrinho, @PathVariable Long id)
			throws ObjectNotFoundException {
		Carrinho carrinhoAtualizado = carrinhoService.atualizarCarrinho(carrinho, id);

		return ResponseEntity.ok().body(carrinhoAtualizado);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		carrinhoRepository.delete(id);

	}

}
