package com.sistema.financiero.repository;

import com.sistema.financiero.model.Inversion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversionRepository extends MongoRepository<Inversion, String> {
    List<Inversion> findByCuentaId(String cuentaId);
    List<Inversion> findByEstaActiva(boolean estaActiva);
    List<Inversion> findByTipoInversion(String tipoInversion);
}

