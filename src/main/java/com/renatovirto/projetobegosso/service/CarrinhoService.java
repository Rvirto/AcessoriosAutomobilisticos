package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.model.Carro;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public Carrinho buscar(Long id) throws ObjectNotFoundException {
		Optional<Carrinho> carro = carrinhoRepository.findById(id);
		
		return carro.orElseThrow(() -> new ObjectNotFoundException(
				"Carro não encontrado! Id: " + id + ", Tipo: " + Carro.class.getName()));
	}
	
	public void adicionarProduto(Produto produto) {
		Carrinho carrinho = new Carrinho();
		carrinho.setIdProduto(produto.getId());
		carrinho.setIdServico(produto.getServico().getId());
		carrinho.setImagem(produto.getImagem());
		carrinho.setProduto(produto.getProduto());
		carrinho.setQuantidade(produto.getQuantidade());
		carrinho.setValor(produto.getPrecoVenda());
		carrinhoRepository.save(carrinho);
	}
	
	public Carrinho atualizarCarrinho(Carrinho carrinho, Long id) throws ObjectNotFoundException {
		Carrinho carroBuscado = buscar(id);
		BeanUtils.copyProperties(carrinho, carroBuscado, "id");
		
		return carrinhoRepository.save(carroBuscado);
		
	}
}
