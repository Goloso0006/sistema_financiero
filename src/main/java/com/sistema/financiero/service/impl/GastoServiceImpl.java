package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.request.GastoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.model.Gasto;
import com.sistema.financiero.repository.GastoRepository;
import com.sistema.financiero.service.GastoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GastoServiceImpl implements GastoService {

    private final GastoRepository gastoRepository;

    @Override
    public MovimientoResponse crearGasto(GastoRequest request) {
        Gasto gasto = Gasto.builder()
                .cuentaId(request.getCuentaId())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .tipoGasto(request.getTipoGasto())
                .esFijo(request.isEsFijo())
                .recordatorio(request.isRecordatorio())
                .fecha(request.getFecha() != null ? request.getFecha() : LocalDate.now())
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
        Gasto gastoGuardado = gastoRepository.save(gasto);
        return mapearAResponse(gastoGuardado);
    }

    @Override
    public MovimientoResponse obtenerGasto(String id) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        return mapearAResponse(gasto);
    }

    @Override
    public List<MovimientoResponse> obtenerGastosPorCuenta(String cuentaId) {
        return gastoRepository.findByCuentaId(cuentaId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerGastosPorTipo(com.sistema.financiero.enums.CategoriaGasto tipoGasto) {
        return gastoRepository.findByTipoGasto(tipoGasto).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerGastosFijos() {
        return gastoRepository.findByEsFijo(true).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoResponse> obtenerTodos() {
        return gastoRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoResponse actualizarGasto(String id, GastoRequest request) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        gasto.setMonto(request.getMonto());
        gasto.setDescripcion(request.getDescripcion());
        gasto.setTipoGasto(request.getTipoGasto());
        gasto.setEsFijo(request.isEsFijo());
        gasto.setRecordatorio(request.isRecordatorio());
        gasto.setFecha(request.getFecha() != null ? request.getFecha() : LocalDate.now());
        gasto.setFechaActualizacion(LocalDateTime.now());
        Gasto gastoActualizado = gastoRepository.save(gasto);
        return mapearAResponse(gastoActualizado);
    }

    @Override
    public void eliminarGasto(String id) {
        gastoRepository.deleteById(id);
    }

    private MovimientoResponse mapearAResponse(Gasto gasto) {
        return MovimientoResponse.builder()
                .id(gasto.getId())
                .cuentaId(gasto.getCuentaId())
                .tipoMovimiento("GASTO")
                .monto(gasto.getMonto())
                .descripcion(gasto.getDescripcion())
                .fecha(gasto.getFecha())
                .fechaCreacion(gasto.getFechaCreacion())
                .build();
    }
}

