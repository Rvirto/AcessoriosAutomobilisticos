package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Servico;
import com.renatovirto.projetobegosso.repository.ServicoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	public Servico buscar(Long id) throws ObjectNotFoundException {
		Servico servico = servicoRepository.findOne(id);
		if (servico == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return servico;
	}
	
	public Servico atualizarServico(Servico servico, Long id) throws ObjectNotFoundException {
		Servico servicoBuscado = buscar(id);
		BeanUtils.copyProperties(servico, servicoBuscado, "id");
		return servicoRepository.save(servicoBuscado);
	}
}
