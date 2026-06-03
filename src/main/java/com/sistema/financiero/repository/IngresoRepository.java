package com.sistema.financiero.repository;

import com.sistema.financiero.model.Ingreso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresoRepository extends MongoRepository<Ingreso, String> {
    List<Ingreso> findByCuentaId(String cuentaId);
    List<Ingreso> findByFrecuencia(com.sistema.financiero.enums.FrecuenciaIngreso frecuencia);
}

