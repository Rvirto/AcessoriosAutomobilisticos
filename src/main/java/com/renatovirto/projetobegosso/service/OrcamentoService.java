package com.renatovirto.projetobegosso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Orcamento calcularTotalProduto(Orcamento orcamento) throws ObjectNotFoundException {
		
		List<Produto> produtos = orcamento.getProdutos();
		
		Double totalOrcamento = 0.0;
		
		
		for (Produto produto : produtos) {
			Long id = produto.getId();
			Optional<Produto> produtoRetornado = produtoRepository.findById(id);
			produto = produtoRetornado.orElseThrow(() -> new ObjectNotFoundException(
					"Produto não foi encontrado!"));
			totalOrcamento+=produto.getPrecoVenda();
			totalOrcamento+=produto.getServico().getValor();
		}
		orcamento.setValorTotal(totalOrcamento);
		
		return orcamento;
	}
}
