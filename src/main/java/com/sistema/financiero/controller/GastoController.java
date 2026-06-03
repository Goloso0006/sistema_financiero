package com.sistema.financiero.controller;

import com.sistema.financiero.dto.request.GastoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.service.GastoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import com.sistema.financiero.enums.CategoriaGasto;

@RestController
@RequestMapping("/api/gastos")
@AllArgsConstructor
public class GastoController {

    private final GastoService gastoService;

    @PostMapping
    public ResponseEntity<MovimientoResponse> crearGasto(@Valid @RequestBody GastoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoService.crearGasto(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> obtenerGasto(@PathVariable String id) {
        return ResponseEntity.ok(gastoService.obtenerGasto(id));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponse>> obtenerTodos() {
        return ResponseEntity.ok(gastoService.obtenerTodos());
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoResponse>> obtenerGastosPorCuenta(@PathVariable String cuentaId) {
        return ResponseEntity.ok(gastoService.obtenerGastosPorCuenta(cuentaId));
    }

    @GetMapping("/tipo/{tipoGasto}")
    public ResponseEntity<List<MovimientoResponse>> obtenerGastosPorTipo(@PathVariable CategoriaGasto tipoGasto) {
        return ResponseEntity.ok(gastoService.obtenerGastosPorTipo(tipoGasto));
    }

    @GetMapping("/fijos")
    public ResponseEntity<List<MovimientoResponse>> obtenerGastosFijos() {
        return ResponseEntity.ok(gastoService.obtenerGastosFijos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponse> actualizarGasto(
            @PathVariable String id,
            @Valid @RequestBody GastoRequest request) {
        return ResponseEntity.ok(gastoService.actualizarGasto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGasto(@PathVariable String id) {
        gastoService.eliminarGasto(id);
        return ResponseEntity.noContent().build();
    }
}

