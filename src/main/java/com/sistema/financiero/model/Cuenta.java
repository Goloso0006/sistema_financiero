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
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "cuentas")
public class Cuenta {
    
    @MongoId
    private String id;
    
    @NotBlank(message = "El ID de usuario no puede estar vacío")
    private String usuarioId;
    
    @NotBlank(message = "El nombre de la cuenta no puede estar vacío")
    private String nombre;
    
    @NotNull(message = "El saldo inicial no puede ser nulo")
    @Positive(message = "El saldo debe ser un valor positivo")
    private double saldo;
    
    private String descripcion;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
    private boolean activa = true;
}


