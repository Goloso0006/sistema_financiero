package com.sistema.financiero.enums;

/**
 * Enum que define las frecuencias de ingresos
 */
public enum FrecuenciaIngreso {
    MENSUAL("Mensual"),
    DIARIO("Diario"),
    QUINCENAL("Quincenal");

    private final String descripcion;

    FrecuenciaIngreso(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

