package com.sistema.financiero.repository;

import com.sistema.financiero.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    List<Evento> findByCuentaId(String cuentaId);
    List<Evento> findByTipoEvento(String tipoEvento);
}

