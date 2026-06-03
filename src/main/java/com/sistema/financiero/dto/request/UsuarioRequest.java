package com.sistema.financiero.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequest {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String correo;
    
    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;
}

