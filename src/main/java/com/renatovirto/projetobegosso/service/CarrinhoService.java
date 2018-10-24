package com.renatovirto.projetobegosso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;
import com.renatovirto.projetobegosso.repository.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Carrinho buscar(Long id) throws ObjectNotFoundException {
		Carrinho carro = carrinhoRepository.findOne(id);

		if (carro == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carro;
	}

	public void atualizarCarrinho(Long id, Long idProduto) throws ObjectNotFoundException {
		Carrinho carrinhoBuscado = carrinhoRepository.findOne(id);
		
		Produto produtoBuscado = produtoRepository.findOne(idProduto);
		
		List<Produto> produtos = carrinhoBuscado.getProdutos();
		
		produtos.remove(produtoBuscado);
		
		carrinhoBuscado.setProdutos(produtos);
		
		carrinhoRepository.save(carrinhoBuscado);
	}

	public Carrinho buscarProdutosDoCliente(Long id) throws ObjectNotFoundException {
		Cliente cliente = clienteService.buscarPeloId(id);

		return carrinhoRepository.findByClienteAndStatus(cliente, "A");
	}

	public Carrinho adicionarNoCarrinho(Long id, Produto produto) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		
		List<Produto> produtos = new ArrayList<Produto>();
		Carrinho carrinho = carrinhoRepository.findByClienteAndStatus(cliente, "A");

		if (carrinho != null) {
			produtos = carrinho.getProdutos();
			produtos.add(produto);
			carrinho.setProdutos(produtos);
		} else {
			carrinho = new Carrinho();
			carrinho.setCliente(cliente);
			produtos.add(produto);
			carrinho.setProdutos(produtos);
		}
		
		return carrinhoRepository.save(carrinho);
	}
}
