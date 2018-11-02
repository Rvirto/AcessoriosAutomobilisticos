package com.renatovirto.projetobegosso.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.model.ProdutoCarrinho;
import com.renatovirto.projetobegosso.repository.OrcamentoRepository;
import com.renatovirto.projetobegosso.repository.ProdutoCarrinhoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OrcamentoService {
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ProdutoCarrinhoRepository produtoCarrinhoRepository;
	
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
		
		List<ProdutoCarrinho> produtoscarrinho = orcamento.getProdutos();
		
		Double totalOrcamento = 0.0;
		
		
		for (ProdutoCarrinho produto : produtoscarrinho) {
			Long id = produto.getId();
			ProdutoCarrinho produtoRetornado = produtoCarrinhoRepository.findOne(id);
			if (produtoRetornado == null) {
				throw new EmptyResultDataAccessException(1);
			}
			produto = produtoRetornado;
			totalOrcamento+=produto.getValorVenda();
			totalOrcamento+=produto.getProduto().getServico().getValor();
		}
		orcamento.setValorTotal(totalOrcamento);
		
		return orcamento;
	}
}
