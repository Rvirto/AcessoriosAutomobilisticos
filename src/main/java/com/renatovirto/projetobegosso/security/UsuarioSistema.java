package com.renatovirto.projetobegosso.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.renatovirto.projetobegosso.model.Cliente;

public class UsuarioSistema extends User{
	
	private Cliente cliente;

	public UsuarioSistema(Cliente cliente, Collection<? extends GrantedAuthority> authorities) {
		super(cliente.getEmail(), cliente.getSenha(), authorities);
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	private static final long serialVersionUID = 1L;

}
