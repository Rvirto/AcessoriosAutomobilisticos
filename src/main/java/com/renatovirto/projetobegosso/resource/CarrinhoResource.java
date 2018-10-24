package com.renatovirto.projetobegosso.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Produto;
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

	@GetMapping
	public List<Carrinho> listarCarrinho() {
		return carrinhoRepository.findAll();
	}

	@PutMapping("/adicionar/{id}")
	public ResponseEntity<Carrinho> adicionar(@Valid @RequestBody Produto produto, @PathVariable Long id)
			throws ObjectNotFoundException {
		Carrinho carrinhoSalvo = carrinhoService.adicionarNoCarrinho(id, produto);

		return ResponseEntity.ok().body(carrinhoSalvo);
	}

	@PutMapping("/remover/{id}/{idProduto}")
	public void remover(@PathVariable Long id, @PathVariable Long idProduto) throws ObjectNotFoundException {
		carrinhoService.atualizarCarrinho(id, idProduto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		carrinhoRepository.delete(id);

	}

	@GetMapping("/{id}")
	public Carrinho retornarCarrinho(@PathVariable Long id) throws ObjectNotFoundException {
		return carrinhoService.buscarProdutosDoCliente(id);
	}
}
