package com.sistema.financiero.enums;

/**
 * Categorías de gasto utilizadas en la aplicación
 */
public enum CategoriaGasto {
	ALIMENTACION("Alimentación"),
	TRANSPORTE("Transporte"),
	VIVIENDA("Vivienda"),
	SALUD("Salud"),
	EDUCACION("Educación"),
	ENTRETENIMIENTO("Entretenimiento"),
	OTROS("Otros");

	private final String descripcion;

	CategoriaGasto(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}


