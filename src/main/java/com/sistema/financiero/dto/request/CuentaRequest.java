package com.sistema.financiero.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaRequest {
    
    @NotBlank(message = "El ID de usuario no puede estar vacío")
    private String usuarioId;
    
    @NotBlank(message = "El nombre de la cuenta no puede estar vacío")
    private String nombre;
    
    @NotNull(message = "El saldo inicial no puede ser nulo")
    @Positive(message = "El saldo debe ser un valor positivo")
    private double saldo;
    
    private String descripcion;
}

