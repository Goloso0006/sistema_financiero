package com.sistema.financiero.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoResponse {
    
    private String id;
    private String cuentaId;
    private String tipoMovimiento;
    private double monto;
    private String descripcion;
    private LocalDate fecha;
    private LocalDateTime fechaCreacion;
}

