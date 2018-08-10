package com.renatovirto.projetobegosso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.OrcamentoRepository;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	public Orcamento buscar(Long id) throws ObjectNotFoundException {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(id);
		
		return orcamento.orElseThrow(() -> new ObjectNotFoundException(
				"Orcamento não encontrado! Id: " + id + ", Tipo: " + ObjectNotFoundException.class.getName()));
	}
	
	public Orcamento atualizarOrcamento(Orcamento orcamento, Long id) throws ObjectNotFoundException {
		Orcamento orcamentoBuscado = buscar(id);
		BeanUtils.copyProperties(orcamento, orcamentoBuscado, "id");
		return orcamentoRepository.save(orcamentoBuscado);
	}
	
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
