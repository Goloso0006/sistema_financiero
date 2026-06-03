package com.sistema.financiero.controller;

import com.sistema.financiero.dto.request.IngresoRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.service.IngresoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
@AllArgsConstructor
public class IngresoController {

    private final IngresoService ingresoService;

    @PostMapping
    public ResponseEntity<MovimientoResponse> crearIngreso(@Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingresoService.crearIngreso(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> obtenerIngreso(@PathVariable String id) {
        return ResponseEntity.ok(ingresoService.obtenerIngreso(id));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponse>> obtenerTodos() {
        return ResponseEntity.ok(ingresoService.obtenerTodos());
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoResponse>> obtenerIngresosPorCuenta(@PathVariable String cuentaId) {
        return ResponseEntity.ok(ingresoService.obtenerIngresosPorCuenta(cuentaId));
    }

    @GetMapping("/frecuencia/{frecuencia}")
    public ResponseEntity<List<MovimientoResponse>> obtenerIngresosPorFrecuencia(@PathVariable String frecuencia) {
        return ResponseEntity.ok(ingresoService.obtenerIngresosPorFrecuencia(frecuencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponse> actualizarIngreso(
            @PathVariable String id,
            @Valid @RequestBody IngresoRequest request) {
        return ResponseEntity.ok(ingresoService.actualizarIngreso(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngreso(@PathVariable String id) {
        ingresoService.eliminarIngreso(id);
        return ResponseEntity.noContent().build();
    }
}

