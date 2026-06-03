package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.sistema.financiero.enums.CategoriaGasto;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "gastos")
public class Gasto {
    
    @MongoId
    private String id;
    
    @NotBlank(message = "El ID de cuenta no puede estar vacío")
    private String cuentaId;
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    @NotNull(message = "La categoría no puede ser nula")
    private CategoriaGasto tipoGasto;
    
    private boolean esFijo = false;
    private boolean recordatorio = false;
    
    private LocalDate fecha = LocalDate.now();
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}



