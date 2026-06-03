package com.sistema.financiero.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import com.sistema.financiero.enums.CategoriaGasto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastoRequest {
    
    @NotBlank(message = "El ID de cuenta no puede estar vacío")
    private String cuentaId;
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "La categoría no puede ser nula")
    private CategoriaGasto tipoGasto;
    
    private boolean esFijo;
    private boolean recordatorio;
    private LocalDate fecha;
}

