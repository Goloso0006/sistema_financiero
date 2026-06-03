package com.sistema.financiero.controller;

import com.sistema.financiero.dto.request.CuentaRequest;
import com.sistema.financiero.dto.response.CuentaResponse;
import com.sistema.financiero.service.CuentaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@AllArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponse> obtenerCuenta(@PathVariable String id) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(id));
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> obtenerTodas() {
        return ResponseEntity.ok(cuentaService.obtenerTodas());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CuentaResponse>> obtenerCuentasPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(cuentaService.obtenerCuentasPorUsuario(usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponse> actualizarCuenta(
            @PathVariable String id,
            @Valid @RequestBody CuentaRequest request) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, request));
    }

    @PatchMapping("/{id}/saldo")
    public ResponseEntity<CuentaResponse> actualizarSaldo(
            @PathVariable String id,
            @RequestParam double nuevoSaldo) {
        return ResponseEntity.ok(cuentaService.actualizarSaldo(id, nuevoSaldo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}

