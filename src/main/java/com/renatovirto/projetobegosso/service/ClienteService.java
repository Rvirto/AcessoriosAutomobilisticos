package com.renatovirto.projetobegosso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.repository.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarPeloId(Long id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente atualizarCliente(Cliente cliente, Long id) throws ObjectNotFoundException {
		Cliente clienteBuscado = buscarPeloId(id);
		BeanUtils.copyProperties(cliente, clienteBuscado, "id");
		return clienteRepository.save(clienteBuscado); 
	}
}
