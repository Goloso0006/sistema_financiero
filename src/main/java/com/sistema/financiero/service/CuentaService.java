package com.sistema.financiero.service;

import com.sistema.financiero.dto.request.CuentaRequest;
import com.sistema.financiero.dto.response.CuentaResponse;

import java.util.List;

public interface CuentaService {
    CuentaResponse crearCuenta(CuentaRequest request);
    CuentaResponse obtenerCuenta(String id);
    List<CuentaResponse> obtenerCuentasPorUsuario(String usuarioId);
    List<CuentaResponse> obtenerTodas();
    CuentaResponse actualizarCuenta(String id, CuentaRequest request);
    CuentaResponse actualizarSaldo(String id, double nuevoSaldo);
    void eliminarCuenta(String id);
}

