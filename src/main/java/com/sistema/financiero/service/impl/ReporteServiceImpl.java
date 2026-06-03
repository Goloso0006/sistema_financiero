package com.sistema.financiero.service.impl;

import com.sistema.financiero.dto.response.ReporteResponse;
import com.sistema.financiero.model.Cuenta;
import com.sistema.financiero.model.Gasto;
import com.sistema.financiero.model.Ingreso;
import com.sistema.financiero.model.Inversion;
import com.sistema.financiero.repository.CuentaRepository;
import com.sistema.financiero.repository.GastoRepository;
import com.sistema.financiero.repository.IngresoRepository;
import com.sistema.financiero.repository.InversionRepository;
import com.sistema.financiero.repository.UsuarioRepository;
import com.sistema.financiero.service.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final GastoRepository gastoRepository;
    private final InversionRepository inversionRepository;
    private final IngresoRepository ingresosRepository; // Nombrado así según el diagrama

    @Override
    public ReporteResponse generarReporte(String usuarioId, int mes, int anio) {
        // Verificar que el usuario exista
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Cuenta> cuentas = cuentaRepository.findByUsuarioId(usuarioId);

        double totalIngresos = 0;
        double totalGastos = 0;
        double totalInversiones = 0;

        for (Cuenta cuenta : cuentas) {
            String cuentaId = cuenta.getId();

            // Sumar ingresos del mes/año
            totalIngresos += ingresosRepository.findByCuentaId(cuentaId).stream()
                    .filter(i -> isSameMonthAndYear(i.getFecha(), mes, anio))
                    .mapToDouble(Ingreso::getMonto)
                    .sum();

            // Sumar gastos del mes/año
            totalGastos += gastoRepository.findByCuentaId(cuentaId).stream()
                    .filter(g -> isSameMonthAndYear(g.getFecha(), mes, anio))
                    .mapToDouble(Gasto::getMonto)
                    .sum();

            // Sumar inversiones del mes/año
            totalInversiones += inversionRepository.findByCuentaId(cuentaId).stream()
                    .filter(i -> isSameMonthAndYear(i.getFecha(), mes, anio))
                    .mapToDouble(Inversion::getMonto)
                    .sum();
        }

        double balance = totalIngresos - totalGastos;

        return ReporteResponse.builder()
                .usuarioId(usuarioId)
                .mes(mes)
                .anio(anio)
                .totalIngresos(totalIngresos)
                .totalGastos(totalGastos)
                .totalInversiones(totalInversiones)
                .balance(balance)
                .build();
    }

    private boolean isSameMonthAndYear(LocalDate fecha, int mes, int anio) {
        if (fecha == null) return false;
        return fecha.getMonthValue() == mes && fecha.getYear() == anio;
    }
}
