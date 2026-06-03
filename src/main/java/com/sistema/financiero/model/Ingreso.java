package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.sistema.financiero.enums.FrecuenciaIngreso;
import java.time.LocalDateTime;
import lombok.Builder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "ingresos")
public class Ingreso extends MovimientoFinanciero {
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "La frecuencia no puede ser nula")
    private FrecuenciaIngreso frecuencia;
    
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
