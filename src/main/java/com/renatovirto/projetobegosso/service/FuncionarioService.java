package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Funcionario;
import com.renatovirto.projetobegosso.repository.FuncionarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario buscar(Long id) throws ObjectNotFoundException {
		Funcionario funcionario = funcionarioRepository.findOne(id);
		if ( funcionario == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return funcionario;
	}
	
	public Funcionario atualizarFuncionario(Funcionario funcionario, Long id) throws ObjectNotFoundException {
		Funcionario funcionarioBuscado = buscar(id);
		BeanUtils.copyProperties(funcionario, funcionarioBuscado, "id");
		return funcionarioRepository.save(funcionarioBuscado);
	}
}
