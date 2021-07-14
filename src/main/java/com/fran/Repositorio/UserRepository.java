package com.fran.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fran.Entidad.Role;

public interface UserRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);
}
