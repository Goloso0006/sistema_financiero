package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.IngresoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.model.Cuenta;
import com.sistema.financiero.model.Ingreso;
import com.sistema.financiero.repository.CuentaRepository;
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
    private final CuentaRepository cuentaRepository;

    @Override
    public MovimientoResponse crearIngreso(IngresoRequest request) {
        Cuenta cuenta = cuentaRepository.findById(request.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

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

        cuenta.setSaldo(cuenta.getSaldo() + request.getMonto());
        cuentaRepository.save(cuenta);

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

        Cuenta cuenta = cuentaRepository.findById(ingreso.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double diferencia = request.getMonto() - ingreso.getMonto();
        cuenta.setSaldo(cuenta.getSaldo() + diferencia);
        cuentaRepository.save(cuenta);

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
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));
        Cuenta cuenta = cuentaRepository.findById(ingreso.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setSaldo(cuenta.getSaldo() - ingreso.getMonto());
        cuentaRepository.save(cuenta);

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

