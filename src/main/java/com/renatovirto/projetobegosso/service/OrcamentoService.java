package com.renatovirto.projetobegosso.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		Orcamento orcamento = orcamentoRepository.findOne(id);
		if (orcamento == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return orcamento;
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
			Produto produtoRetornado = produtoRepository.findOne(id);
			if (produtoRetornado == null) {
				throw new EmptyResultDataAccessException(1);
			}
			produto = produtoRetornado;
			totalOrcamento+=produto.getPrecoVenda();
			totalOrcamento+=produto.getServico().getValor();
		}
		orcamento.setValorTotal(totalOrcamento);
		
		return orcamento;
	}
}
