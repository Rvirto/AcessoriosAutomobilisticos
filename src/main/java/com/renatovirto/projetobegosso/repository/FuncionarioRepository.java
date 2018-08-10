package com.renatovirto.projetobegosso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetobegosso.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
