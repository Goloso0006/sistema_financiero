package com.sistema.financiero.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    
    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private boolean activo;
}

