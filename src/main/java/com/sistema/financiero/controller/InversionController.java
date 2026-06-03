package com.sistema.financiero.controller;

import com.sistema.financiero.dto.request.InversionRequest;
import com.sistema.financiero.dto.response.MovimientoResponse;
import com.sistema.financiero.service.InversionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import com.sistema.financiero.enums.TipoInversion;

@RestController
@RequestMapping("/api/inversiones")
@AllArgsConstructor
public class InversionController {

    private final InversionService inversionService;

    @PostMapping
    public ResponseEntity<MovimientoResponse> crearInversion(@Valid @RequestBody InversionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inversionService.crearInversion(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> obtenerInversion(@PathVariable String id) {
        return ResponseEntity.ok(inversionService.obtenerInversion(id));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponse>> obtenerTodas() {
        return ResponseEntity.ok(inversionService.obtenerTodas());
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoResponse>> obtenerInversionesPorCuenta(@PathVariable String cuentaId) {
        return ResponseEntity.ok(inversionService.obtenerInversionesPorCuenta(cuentaId));
    }

    @GetMapping("/activas")
    public ResponseEntity<List<MovimientoResponse>> obtenerInversionesActivas() {
        return ResponseEntity.ok(inversionService.obtenerInversionesActivas());
    }

    @GetMapping("/tipo/{tipoInversion}")
    public ResponseEntity<List<MovimientoResponse>> obtenerInversionesPorTipo(@PathVariable TipoInversion tipoInversion) {
        return ResponseEntity.ok(inversionService.obtenerInversionesPorTipo(tipoInversion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponse> actualizarInversion(
            @PathVariable String id,
            @Valid @RequestBody InversionRequest request) {
        return ResponseEntity.ok(inversionService.actualizarInversion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInversion(@PathVariable String id) {
        inversionService.eliminarInversion(id);
        return ResponseEntity.noContent().build();
    }
}

