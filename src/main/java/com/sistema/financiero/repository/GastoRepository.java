package com.sistema.financiero.repository;

import com.sistema.financiero.model.Gasto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends MongoRepository<Gasto, String> {
    List<Gasto> findByCuentaId(String cuentaId);
    List<Gasto> findByTipoGasto(com.sistema.financiero.enums.CategoriaGasto tipoGasto);
    List<Gasto> findByEsFijo(boolean esFijo);
}

