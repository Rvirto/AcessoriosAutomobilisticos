package com.renatovirto.projetobegosso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.model.Orcamento;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.model.ProdutoCarrinho;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;
import com.renatovirto.projetobegosso.repository.ClienteRepository;
import com.renatovirto.projetobegosso.repository.OrcamentoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OrcamentoService {
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
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
	
	public Orcamento calcularTotalProduto(List<ProdutoCarrinho> produtoscarrinho, 
			Long idCliente, Long idCarrinho) throws ObjectNotFoundException {
		Cliente clienteBuscado = clienteRepository.findOne(idCliente);
		Carrinho carrinhoBuscado = carrinhoRepository.findOne(idCarrinho);
		
		carrinhoBuscado.setStatus("F");
		carrinhoRepository.save(carrinhoBuscado);
		
		List<Produto> produtos = new ArrayList<>();
		Double totalOrcamento = 0.0;
		
		for (ProdutoCarrinho produto : produtoscarrinho) {
			totalOrcamento+=produto.getValorVenda();
			totalOrcamento+=produto.getProduto().getServico().getValor()*produto.getQuantidade();
			produtos.add(produto.getProduto());
		}
		Orcamento orcamento = new Orcamento();
		orcamento.setValorTotal(totalOrcamento);
		orcamento.setCliente(clienteBuscado);
		orcamento.setDesconto(0.0);
		orcamento.setProdutos(produtos);
		
		return orcamentoRepository.save(orcamento);
	}
	
	public List<Orcamento> buscarMeusOrcamentos(Long id) {
		Cliente clienteBuscado = clienteRepository.findOne(id);
		
		List<Orcamento> orcamentoBuscado = orcamentoRepository.findByCliente(clienteBuscado);
		
		if (orcamentoBuscado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return orcamentoBuscado;
	}
}
