package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Carro;
import com.renatovirto.projetobegosso.repository.CarroRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public Carro buscar(Long id) throws ObjectNotFoundException {
		Optional<Carro> carro = carroRepository.findById(id);
		
		return carro.orElseThrow(() -> new ObjectNotFoundException(
				"Carro não encontrado! Id: " + id + ", Tipo: " + Carro.class.getName()));
	}
	
	public Carro atualizarCarro(Carro carro, Long id) throws ObjectNotFoundException {
		Carro carroBuscado = buscar(id);
		BeanUtils.copyProperties(carro, carroBuscado, "id");
		
		return carroRepository.save(carroBuscado);
	}
}
