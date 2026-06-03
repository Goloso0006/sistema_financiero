package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.CuentaRequest;
import com.sistema.financiero.dto.response.CuentaResponse;
import com.sistema.financiero.model.Cuenta;
import com.sistema.financiero.repository.CuentaRepository;
import com.sistema.financiero.service.CuentaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    @Override
    public CuentaResponse crearCuenta(CuentaRequest request) {
        Cuenta cuenta = Cuenta.builder()
                .usuarioId(request.getUsuarioId())
                .nombre(request.getNombre())
                .saldo(request.getSaldo())
                .descripcion(request.getDescripcion())
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .activa(true)
                .build();
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        return mapearAResponse(cuentaGuardada);
    }

    @Override
    public CuentaResponse obtenerCuenta(String id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapearAResponse(cuenta);
    }

    @Override
    public List<CuentaResponse> obtenerCuentasPorUsuario(String usuarioId) {
        return cuentaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaResponse> obtenerTodas() {
        return cuentaRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaResponse actualizarCuenta(String id, CuentaRequest request) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setNombre(request.getNombre());
        cuenta.setDescripcion(request.getDescripcion());
        cuenta.setFechaActualizacion(LocalDateTime.now());
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);
        return mapearAResponse(cuentaActualizada);
    }

    @Override
    public CuentaResponse actualizarSaldo(String id, double nuevoSaldo) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setSaldo(nuevoSaldo);
        cuenta.setFechaActualizacion(LocalDateTime.now());
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);
        return mapearAResponse(cuentaActualizada);
    }

    @Override
    public void eliminarCuenta(String id) {
        cuentaRepository.deleteById(id);
    }

    private CuentaResponse mapearAResponse(Cuenta cuenta) {
        return CuentaResponse.builder()
                .id(cuenta.getId())
                .usuarioId(cuenta.getUsuarioId())
                .nombre(cuenta.getNombre())
                .saldo(cuenta.getSaldo())
                .descripcion(cuenta.getDescripcion())
                .fechaCreacion(cuenta.getFechaCreacion())
                .fechaActualizacion(cuenta.getFechaActualizacion())
                .activa(cuenta.isActiva())
                .build();
    }
}

