# Guía de Pruebas de Endpoints en Postman

Este documento detalla todos los endpoints disponibles en el **Sistema Financiero**, explicando cómo estructurar las peticiones para que puedas probarlas fácilmente desde **Postman**.

La URL base para todas las peticiones asumiendo que ejecutas el proyecto localmente es:
`http://localhost:8080` *(Asegúrate de ajustar el puerto si tu aplicación usa uno distinto).*

---

## 1. Usuarios (`/api/usuarios`)

### Crear un Usuario
* **Método:** `POST`
* **URL:** `/api/usuarios`
* **Body (JSON):**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan.perez@email.com",
  "telefono": "3001234567",
  "contrasena": "secreta123"
}
```

### Obtener Todos los Usuarios
* **Método:** `GET`
* **URL:** `/api/usuarios`

### Obtener Usuario por ID
* **Método:** `GET`
* **URL:** `/api/usuarios/{id}` *(Reemplaza `{id}` por el ID generado)*

### Obtener Usuario por Correo
* **Método:** `GET`
* **URL:** `/api/usuarios/correo/{correo}` *(Ej. `/api/usuarios/correo/juan.perez@email.com`)*

### Actualizar un Usuario
* **Método:** `PUT`
* **URL:** `/api/usuarios/{id}`
* **Body (JSON):** (Mismos campos que al crear, con los datos actualizados).

### Eliminar un Usuario
* **Método:** `DELETE`
* **URL:** `/api/usuarios/{id}`

---

## 2. Cuentas (`/api/cuentas`)

### Crear una Cuenta
* **Método:** `POST`
* **URL:** `/api/cuentas`
* **Body (JSON):**
```json
{
  "usuarioId": "ID_DEL_USUARIO",
  "nombre": "Cuenta de Ahorros",
  "saldo": 1500000,
  "descripcion": "Cuenta principal para ahorros mensuales"
}
```

### Obtener Todas las Cuentas
* **Método:** `GET`
* **URL:** `/api/cuentas`

### Obtener Cuenta por ID
* **Método:** `GET`
* **URL:** `/api/cuentas/{id}`

### Obtener Cuentas de un Usuario
* **Método:** `GET`
* **URL:** `/api/cuentas/usuario/{usuarioId}`

### Actualizar una Cuenta
* **Método:** `PUT`
* **URL:** `/api/cuentas/{id}`
* **Body (JSON):** (Mismos campos de creación).

### Actualizar Saldo de Cuenta
* **Método:** `PATCH`
* **URL:** `/api/cuentas/{id}/saldo?nuevoSaldo=2000000` *(Nota: El saldo va como query param)*

### Eliminar una Cuenta
* **Método:** `DELETE`
* **URL:** `/api/cuentas/{id}`

---

## 3. Ingresos (`/api/ingresos`)

### Crear un Ingreso
* **Método:** `POST`
* **URL:** `/api/ingresos`
* **Body (JSON):**
```json
{
  "cuentaId": "ID_DE_LA_CUENTA",
  "monto": 500000,
  "descripcion": "Pago de nómina",
  "frecuencia": "MENSUAL", 
  "fecha": "2023-10-15"
}
```

### Obtener Todos los Ingresos
* **Método:** `GET`
* **URL:** `/api/ingresos`

### Obtener Ingreso por ID
* **Método:** `GET`
* **URL:** `/api/ingresos/{id}`

### Obtener Ingresos de una Cuenta
* **Método:** `GET`
* **URL:** `/api/ingresos/cuenta/{cuentaId}`

### Obtener Ingresos por Frecuencia
* **Método:** `GET`
* **URL:** `/api/ingresos/frecuencia/{frecuencia}` *(Ej. `/api/ingresos/frecuencia/MENSUAL`)*

### Actualizar un Ingreso
* **Método:** `PUT`
* **URL:** `/api/ingresos/{id}`
* **Body (JSON):** (Mismos campos de creación).

### Eliminar un Ingreso
* **Método:** `DELETE`
* **URL:** `/api/ingresos/{id}`

---

## 4. Gastos (`/api/gastos`)

### Crear un Gasto
* **Método:** `POST`
* **URL:** `/api/gastos`
* **Body (JSON):**
```json
{
  "cuentaId": "ID_DE_LA_CUENTA",
  "monto": 120000,
  "descripcion": "Mercado quincenal",
  "tipoGasto": "ALIMENTACION", 
  "esFijo": true,
  "recordatorio": false,
  "fecha": "2023-10-16"
}
```

### Obtener Todos los Gastos
* **Método:** `GET`
* **URL:** `/api/gastos`

### Obtener Gastos de una Cuenta
* **Método:** `GET`
* **URL:** `/api/gastos/cuenta/{cuentaId}`

### Obtener Gastos por Tipo (Categoría)
* **Método:** `GET`
* **URL:** `/api/gastos/tipo/{tipoGasto}` *(Ej. `/api/gastos/tipo/ALIMENTACION`)*

### Obtener Gastos Fijos
* **Método:** `GET`
* **URL:** `/api/gastos/fijos`

### Actualizar y Eliminar Gasto
* **PUT** `/api/gastos/{id}` -> Actualizar (enviar JSON completo).
* **DELETE** `/api/gastos/{id}` -> Eliminar.

---

## 5. Inversiones (`/api/inversiones`)

### Crear una Inversión
* **Método:** `POST`
* **URL:** `/api/inversiones`
* **Body (JSON):**
```json
{
  "cuentaId": "ID_DE_LA_CUENTA",
  "monto": 1000000,
  "descripcion": "CDT Banco XYZ",
  "tipoInversion": "CDT",
  "rentabilidadEsperada": 0.05,
  "fecha": "2023-10-01",
  "fechaVencimiento": "2024-10-01"
}
```

### Obtener Todas las Inversiones
* **Método:** `GET`
* **URL:** `/api/inversiones`

### Obtener Inversiones de una Cuenta
* **Método:** `GET`
* **URL:** `/api/inversiones/cuenta/{cuentaId}`

### Obtener Inversiones Activas
* **Método:** `GET`
* **URL:** `/api/inversiones/activas`

### Obtener Inversiones por Tipo
* **Método:** `GET`
* **URL:** `/api/inversiones/tipo/{tipoInversion}` *(Ej. `/api/inversiones/tipo/CDT`)*

### Actualizar y Eliminar Inversión
* **PUT** `/api/inversiones/{id}` -> Actualizar (enviar JSON completo).
* **DELETE** `/api/inversiones/{id}` -> Eliminar.

---

## 6. Reportes (`/api/reportes`)

### Generar Reporte Mensual
Este endpoint genera un resumen del balance general, total de ingresos, gastos e inversiones para un usuario específico en un mes y año determinados.

* **Método:** `GET`
* **URL:** `/api/reportes/generar?usuarioId={usuarioId}&mes={mes}&anio={anio}`
* **Parámetros (Query Params):**
  * `usuarioId`: ID del usuario
  * `mes`: Número de mes (ej. `10` para Octubre)
  * `anio`: Año en cuatro dígitos (ej. `2023`)

**Ejemplo de URL completa:**
`http://localhost:8080/api/reportes/generar?usuarioId=AQUI_TU_USUARIO_ID&mes=10&anio=2023`

*(Nota: Dado tu `application.properties`, recuerda agregar `/api/v1` al inicio en Postman: `http://localhost:8080/api/v1/api/reportes/generar...`)*
