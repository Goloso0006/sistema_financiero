package com.sistema.financiero.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngresoRequest {
    
    @NotBlank(message = "El ID de cuenta no puede estar vacío")
    private String cuentaId;
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotBlank(message = "La frecuencia no puede estar vacía")
    private String frecuencia;
    
    private LocalDate fecha;
}

