package com.sistema.financiero.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteResponse {
    private String usuarioId;
    private int mes;
    private int anio;
    private double totalIngresos;
    private double totalGastos;
    private double totalInversiones;
    private double balance;
}
