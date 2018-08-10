package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Servico;
import com.renatovirto.projetobegosso.repository.ServicoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	public Servico buscar(Long id) throws ObjectNotFoundException {
		Optional<Servico> servico = servicoRepository.findById(id);
		
		return servico.orElseThrow(() -> new ObjectNotFoundException(
				"Serviço não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
	}
	
	public Servico atualizarServico(Servico servico, Long id) throws ObjectNotFoundException {
		Servico servicoBuscado = buscar(id);
		BeanUtils.copyProperties(servico, servicoBuscado, "id");
		return servicoRepository.save(servicoBuscado);
	}
}
