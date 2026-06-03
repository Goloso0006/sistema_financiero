package com.sistema.financiero.service;

import com.sistema.financiero.dto.request.IngresoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;

import java.util.List;

public interface IngresoService {
    MovimientoResponse crearIngreso(IngresoRequest request);
    MovimientoResponse obtenerIngreso(String id);
    List<MovimientoResponse> obtenerIngresosPorCuenta(String cuentaId);
    List<MovimientoResponse> obtenerIngresosPorFrecuencia(com.sistema.financiero.enums.FrecuenciaIngreso frecuencia);
    List<MovimientoResponse> obtenerTodos();
    MovimientoResponse actualizarIngreso(String id, IngresoRequest request);
    void eliminarIngreso(String id);
}

