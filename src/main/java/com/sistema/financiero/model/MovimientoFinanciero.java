package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.MongoId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class MovimientoFinanciero {
    
    @MongoId
    private String id;
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;
    
    @Builder.Default
    private LocalDate fecha = LocalDate.now();
    
    @NotBlank(message = "El ID de cuenta no puede estar vacío")
    private String cuentaId;
}
