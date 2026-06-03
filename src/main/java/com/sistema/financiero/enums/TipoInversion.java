package com.sistema.financiero.enums;

/**
 * Tipos de inversión soportados
 */
public enum TipoInversion {
	GANADERIA("Ganadería"),
	DEPORTE("Deporte"),
	CDT("CDT"),
	VIVIENDA("Vivienda"),
	EMPRESA("Empresa");

	private final String descripcion;

	TipoInversion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}


