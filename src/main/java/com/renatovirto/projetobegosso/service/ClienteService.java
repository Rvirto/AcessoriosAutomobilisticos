package com.renatovirto.projetobegosso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.repository.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarPeloId(Long id) throws ObjectNotFoundException {
		Cliente cliente = clienteRepository.findOne(id);
		
		if(cliente == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cliente;
	}
	
	public Cliente atualizarCliente(Cliente cliente, Long id) throws ObjectNotFoundException {
		Cliente clienteBuscado = buscarPeloId(id);
		BeanUtils.copyProperties(cliente, clienteBuscado, "id");
		return clienteRepository.save(clienteBuscado); 
	}
}
