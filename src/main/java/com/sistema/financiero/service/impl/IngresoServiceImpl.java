package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.IngresoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.model.Ingreso;
import com.sistema.financiero.repository.IngresoRepository;
import com.sistema.financiero.service.IngresoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository ingresoRepository;

    @Override
    public MovimientoResponse crearIngreso(IngresoRequest request) {
        Ingreso ingreso = Ingreso.builder()
                .cuentaId(request.getCuentaId())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .frecuencia(request.getFrecuencia())
                .fecha(request.getFecha() != null ? request.getFecha() : LocalDate.now())
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);
        return mapearAResponse(ingresoGuardado);
    }

    @Override
    public MovimientoResponse obtenerIngreso(String id) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));
        return mapearAResponse(ingreso);
    }

    @Override
    public List<MovimientoResponse> obtenerIngresosPorCuenta(String cuentaId) {
        return ingresoRepository.findByCuentaId(cuentaId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerIngresosPorFrecuencia(com.sistema.financiero.enums.FrecuenciaIngreso frecuencia) {
        return ingresoRepository.findByFrecuencia(frecuencia).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerTodos() {
        return ingresoRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponse actualizarIngreso(String id, IngresoRequest request) {
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));
        ingreso.setMonto(request.getMonto());
        ingreso.setDescripcion(request.getDescripcion());
        ingreso.setFrecuencia(request.getFrecuencia());
        ingreso.setFecha(request.getFecha() != null ? request.getFecha() : LocalDate.now());
        ingreso.setFechaActualizacion(LocalDateTime.now());
        Ingreso ingresoActualizado = ingresoRepository.save(ingreso);
        return mapearAResponse(ingresoActualizado);
    }

    @Override
    public void eliminarIngreso(String id) {
        ingresoRepository.deleteById(id);
    }

    private MovimientoResponse mapearAResponse(Ingreso ingreso) {
        return MovimientoResponse.builder()
                .id(ingreso.getId())
                .cuentaId(ingreso.getCuentaId())
                .tipoMovimiento("INGRESO")
                .monto(ingreso.getMonto())
                .descripcion(ingreso.getDescripcion())
                .fecha(ingreso.getFecha())
                .fechaCreacion(ingreso.getFechaCreacion())
                .build();
    }
}

