package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carro;
import com.renatovirto.projetobegosso.repository.CarroRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public Carro buscar(Long id) throws ObjectNotFoundException {
		Carro carro = carroRepository.findOne(id);
		
		if (carro == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return carro;
	}
	
	public Carro atualizarCarro(Carro carro, Long id) throws ObjectNotFoundException {
		Carro carroBuscado = buscar(id);
		BeanUtils.copyProperties(carro, carroBuscado, "id");
		
		return carroRepository.save(carroBuscado);
	}
}
