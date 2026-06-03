package com.sistema.financiero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "eventos")
public class Evento {
    
    @MongoId
    private String id;
    
    @NotBlank(message = "El ID de cuenta no puede estar vacío")
    private String cuentaId;
    
    @NotBlank(message = "El tipo de evento no puede estar vacío")
    private String tipoEvento;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    
    private String referencia;
    private LocalDateTime fechaEvento = LocalDateTime.now();
    private boolean notificado = false;
}


