package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.UsuarioRequest;
import com.sistema.financiero.dto.response.UsuarioResponse;
import com.sistema.financiero.model.Usuario;
import com.sistema.financiero.repository.UsuarioRepository;
import com.sistema.financiero.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .contrasena(request.getContrasena())
                .activo(true)
                .build();
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return mapearAResponse(usuarioGuardado);
    }

    @Override
    public UsuarioResponse obtenerUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapearAResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse actualizarUsuario(String id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return mapearAResponse(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponse obtenerPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapearAResponse(usuario);
    }

    private UsuarioResponse mapearAResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .activo(usuario.isActivo())
                .build();
    }
}

