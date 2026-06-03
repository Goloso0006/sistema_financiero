package com.sistema.financiero.repository;

import com.sistema.financiero.model.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta, String> {
    List<Cuenta> findByUsuarioId(String usuarioId);
    Optional<Cuenta> findByIdAndUsuarioId(String id, String usuarioId);
}

