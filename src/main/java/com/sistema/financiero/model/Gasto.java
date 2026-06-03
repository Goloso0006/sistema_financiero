package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.sistema.financiero.enums.CategoriaGasto;
import java.time.LocalDateTime;
import lombok.Builder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "gastos")
public class Gasto extends MovimientoFinanciero {
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "La categoría no puede ser nula")
    private CategoriaGasto tipoGasto;
    
    @Builder.Default
    private boolean esFijo = false;
    
    @Builder.Default
    private boolean recordatorio = false;
    
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}