package com.sistema.financiero.service;

import com.sistema.financiero.dto.request.GastoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;

import java.util.List;

public interface GastoService {
    MovimientoResponse crearGasto(GastoRequest request);
    MovimientoResponse obtenerGasto(String id);
    List<MovimientoResponse> obtenerGastosPorCuenta(String cuentaId);
    List<MovimientoResponse> obtenerGastosPorTipo(com.sistema.financiero.enums.CategoriaGasto tipoGasto);
    List<MovimientoResponse> obtenerGastosFijos();
    List<MovimientoResponse> obtenerTodos();
    MovimientoResponse actualizarGasto(String id, GastoRequest request);
    void eliminarGasto(String id);
}

