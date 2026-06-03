package com.sistema.financiero.controller;

import com.sistema.financiero.dto.response.ReporteResponse;
import com.sistema.financiero.service.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@AllArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/generar")
    public ResponseEntity<ReporteResponse> generarReporte(
            @RequestParam String usuarioId,
            @RequestParam int mes,
            @RequestParam int anio) {
        return ResponseEntity.ok(reporteService.generarReporte(usuarioId, mes, anio));
    }
}
