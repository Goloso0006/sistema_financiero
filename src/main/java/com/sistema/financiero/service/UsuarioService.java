package com.sistema.financiero.service;

import com.sistema.financiero.dto.request.UsuarioRequest;
import com.sistema.financiero.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse obtenerUsuario(String id);
    List<UsuarioResponse> obtenerTodos();
    UsuarioResponse actualizarUsuario(String id, UsuarioRequest request);
    void eliminarUsuario(String id);
    UsuarioResponse obtenerPorCorreo(String correo);
}

