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
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.model.ProdutoCarrinho;
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
		return orcamentoService.buscar(id);
	}
	
	@PostMapping("/{idCliente}/{idCarrinho}")
	public ResponseEntity<Orcamento> adicionar(@Valid @RequestBody List<ProdutoCarrinho> produtoscarrinho,
			@PathVariable Long idCliente, @PathVariable Long idCarrinho)
			throws ObjectNotFoundException {
		Orcamento orcamentoSalvo = orcamentoService.calcularTotalProduto
				(produtoscarrinho, idCliente, idCarrinho);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Orcamento> atualizar(@Valid @RequestBody Orcamento orcamento, @PathVariable Long id) throws ObjectNotFoundException {
		Orcamento orcamentoSalvo = orcamentoService.atualizarOrcamento(orcamento, id);
		
		return ResponseEntity.ok().body(orcamentoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		orcamentoRepository.delete(id);
	}
	
	@GetMapping("/meusOrcamentos/{idCliente}")
	public List<Orcamento> meusOrcamentos(@PathVariable Long idCliente) {
		return orcamentoService.buscarMeusOrcamentos(idCliente);
	}
}
