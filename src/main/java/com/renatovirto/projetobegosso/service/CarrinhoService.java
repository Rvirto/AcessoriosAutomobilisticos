package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carrinho;
import com.renatovirto.projetobegosso.repository.CarrinhoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public Carrinho buscar(Long id) throws ObjectNotFoundException {
		Carrinho carro = carrinhoRepository.findOne(id);
		
		if(carro == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carro;
	}
	
	public Carrinho atualizarCarrinho(Carrinho carrinho, Long id) throws ObjectNotFoundException {
		Carrinho carroBuscado = buscar(id);
		BeanUtils.copyProperties(carrinho, carroBuscado, "id");
		
		return carrinhoRepository.save(carroBuscado);
		
	}
}
