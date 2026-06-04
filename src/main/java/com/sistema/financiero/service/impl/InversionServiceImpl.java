package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.InversionRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.model.Cuenta;
import com.sistema.financiero.model.Inversion;
import com.sistema.financiero.repository.CuentaRepository;
import com.sistema.financiero.repository.InversionRepository;
import com.sistema.financiero.service.InversionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InversionServiceImpl implements InversionService {

    private final InversionRepository inversionRepository;
    private final CuentaRepository cuentaRepository;

    @Override
    public MovimientoResponse crearInversion(InversionRequest request) {
        Cuenta cuenta = cuentaRepository.findById(request.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Inversion inversion = Inversion.builder()
                .cuentaId(request.getCuentaId())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .tipoInversion(request.getTipoInversion())
                .rentabilidadEsperada(request.getRentabilidadEsperada())
                .fecha(request.getFecha() != null ? request.getFecha() : LocalDate.now())
                .fechaVencimiento(request.getFechaVencimiento())
                .estaActiva(true)
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
        Inversion inversionGuardada = inversionRepository.save(inversion);

        cuenta.setSaldo(cuenta.getSaldo() - request.getMonto());
        cuentaRepository.save(cuenta);

        return mapearAResponse(inversionGuardada);
    }

    @Override
    public MovimientoResponse obtenerInversion(String id) {
        Inversion inversion = inversionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inversión no encontrada"));
        return mapearAResponse(inversion);
    }

    @Override
    public List<MovimientoResponse> obtenerInversionesPorCuenta(String cuentaId) {
        return inversionRepository.findByCuentaId(cuentaId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerInversionesActivas() {
        return inversionRepository.findByEstaActiva(true).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerInversionesPorTipo(com.sistema.financiero.enums.TipoInversion tipoInversion) {
        return inversionRepository.findByTipoInversion(tipoInversion).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerTodas() {
        return inversionRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponse actualizarInversion(String id, InversionRequest request) {
        Inversion inversion = inversionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inversión no encontrada"));

        Cuenta cuenta = cuentaRepository.findById(inversion.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double diferencia = request.getMonto() - inversion.getMonto();
        cuenta.setSaldo(cuenta.getSaldo() - diferencia);
        cuentaRepository.save(cuenta);

        inversion.setMonto(request.getMonto());
        inversion.setDescripcion(request.getDescripcion());
        inversion.setTipoInversion(request.getTipoInversion());
        inversion.setRentabilidadEsperada(request.getRentabilidadEsperada());
        inversion.setFecha(request.getFecha() != null ? request.getFecha() : LocalDate.now());
        inversion.setFechaVencimiento(request.getFechaVencimiento());
        inversion.setFechaActualizacion(LocalDateTime.now());
        Inversion inversionActualizada = inversionRepository.save(inversion);
        return mapearAResponse(inversionActualizada);
    }

    @Override
    public void eliminarInversion(String id) {
        Inversion inversion = inversionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inversión no encontrada"));
        Cuenta cuenta = cuentaRepository.findById(inversion.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setSaldo(cuenta.getSaldo() + inversion.getMonto());
        cuentaRepository.save(cuenta);

        inversionRepository.deleteById(id);
    }

    private MovimientoResponse mapearAResponse(Inversion inversion) {
        return MovimientoResponse.builder()
                .id(inversion.getId())
                .cuentaId(inversion.getCuentaId())
                .tipoMovimiento("INVERSION")
                .monto(inversion.getMonto())
                .descripcion(inversion.getDescripcion())
                .fecha(inversion.getFecha())
                .fechaCreacion(inversion.getFechaCreacion())
                .build();
    }
}

