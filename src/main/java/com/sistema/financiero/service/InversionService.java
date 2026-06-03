package com.sistema.financiero.service;

import com.sistema.financiero.dto.request.InversionRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;

import java.util.List;

public interface InversionService {
    MovimientoResponse crearInversion(InversionRequest request);
    MovimientoResponse obtenerInversion(String id);
    List<MovimientoResponse> obtenerInversionesPorCuenta(String cuentaId);
    List<MovimientoResponse> obtenerInversionesActivas();
    List<MovimientoResponse> obtenerInversionesPorTipo(String tipoInversion);
    List<MovimientoResponse> obtenerTodas();
    MovimientoResponse actualizarInversion(String id, InversionRequest request);
    void eliminarInversion(String id);
}

