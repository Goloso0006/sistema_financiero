package com.sistema.financiero.service;

import com.sistema.financiero.dto.response.ReporteResponse;

public interface ReporteService {
    ReporteResponse generarReporte(String usuarioId, int mes, int anio);
}
