package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Fornecedor;
import com.renatovirto.projetobegosso.repository.FornecedorRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor buscar(Long id) throws ObjectNotFoundException {
		Fornecedor fornecedor = fornecedorRepository.findOne(id);
		
		if (fornecedor == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return fornecedor;
	}
	
	public Fornecedor atualizarFornecedor(Fornecedor fornecedor, Long id) throws ObjectNotFoundException {
		Fornecedor fornecedorBuscado = buscar(id);
		BeanUtils.copyProperties(fornecedor, fornecedorBuscado, "id");
		return fornecedorRepository.save(fornecedorBuscado);
	}
}
