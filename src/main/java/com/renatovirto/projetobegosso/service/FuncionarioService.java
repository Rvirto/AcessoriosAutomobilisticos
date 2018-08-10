package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Funcionario;
import com.renatovirto.projetobegosso.repository.FuncionarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario buscar(Long id) throws ObjectNotFoundException {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
	}
	
	public Funcionario atualizarFuncionario(Funcionario funcionario, Long id) throws ObjectNotFoundException {
		Funcionario funcionarioBuscado = buscar(id);
		BeanUtils.copyProperties(funcionario, funcionarioBuscado, "id");
		return funcionarioRepository.save(funcionarioBuscado);
	}
}
