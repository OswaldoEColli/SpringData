package com.ejemplo.transferenciabancaria.repository;

import com.ejemplo.transferenciabancaria.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para operaciones CRUD sobre entidades Cuenta.
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}