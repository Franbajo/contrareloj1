package com.fran.Repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fran.Entidad.Clientes;
@Repository
public interface IClientesRepo extends CrudRepository<Clientes, Long> {

}
