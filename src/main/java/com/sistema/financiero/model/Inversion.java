package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.sistema.financiero.enums.TipoInversion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "inversiones")
public class Inversion extends MovimientoFinanciero {
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "El tipo de inversión no puede ser nulo")
    private TipoInversion tipoInversion;
    
    @Builder.Default
    private boolean estaActiva = true;
    
    @Builder.Default
    private double rentabilidadEsperada = 0;
    
    private LocalDate fechaVencimiento;
    
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @Builder.Default
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}
