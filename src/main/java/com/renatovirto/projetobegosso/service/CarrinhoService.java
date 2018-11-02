package com.renatovirto.projetobegosso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.model.ProdutoCarrinho;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;
import com.renatovirto.projetobegosso.repository.ProdutoCarrinhoRepository;
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
	
	@Autowired
	private ProdutoCarrinhoRepository produtoCarrinhoRepository;

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
		
		List<ProdutoCarrinho> produtosCarrinho = carrinhoBuscado.getProdutoCarrinho();
		
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			System.out.println(produtoCarrinho.getValorVenda());
			if (produtoCarrinho.getProduto().getId() == produtoBuscado.getId()) {
				produtosCarrinho.remove(produtoCarrinho);
				produtoCarrinhoRepository.delete(produtoCarrinho);
				break;
			}
		}	
		carrinhoBuscado.setProdutoCarrinho(produtosCarrinho);
		
		carrinhoRepository.save(carrinhoBuscado);
	}

	public Carrinho buscarProdutosDoCliente(Long id) throws ObjectNotFoundException {
		Cliente cliente = clienteService.buscarPeloId(id);

		return carrinhoRepository.findByClienteAndStatus(cliente, "A");
	}

	public Carrinho adicionarNoCarrinho(Long id, Produto produto) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		List<ProdutoCarrinho> produtos = new ArrayList<ProdutoCarrinho>();
		Carrinho carrinho = carrinhoRepository.findByClienteAndStatus(cliente, "A");
		ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho();

		produtoCarrinho.setProduto(produto);
		produtoCarrinho.setQuantidade(produto.getQuantidade());
		produtoCarrinho.setValorVenda(produto.getPrecoVenda());
		
		produtoCarrinhoRepository.save(produtoCarrinho);
		
		if (carrinho != null) {
			produtos = carrinho.getProdutoCarrinho();
			produtos.add(produtoCarrinho);
			carrinho.setProdutoCarrinho(produtos);
		} else {
			carrinho = new Carrinho();
			carrinho.setCliente(cliente);
			produtos.add(produtoCarrinho);
			carrinho.setProdutoCarrinho(produtos);
		}
		
		return carrinhoRepository.save(carrinho);
	}
}
