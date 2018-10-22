package com.renatovirto.projetobegosso.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetobegosso.model.Cliente;
import com.renatovirto.projetobegosso.repository.ClienteRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if ( cliente == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return new UsuarioSistema(cliente, getPermissao(cliente));
	}

	private Collection<? extends GrantedAuthority> getPermissao(Cliente cliente) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(cliente.getTipoPermissao().toString()));
		return authorities;
	}
	
}
