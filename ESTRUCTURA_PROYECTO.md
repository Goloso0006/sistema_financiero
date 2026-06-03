# Sistema Financiero - Documentación de Estructura

## 📋 Descripción General
Sistema de gestión financiera basado en **Spring Boot 3** con **MongoDB** como base de datos. Estructura completa de capas con modelos, servicios, repositorios y controladores REST.

## 🏗️ Estructura de Capas

```
src/main/java/com/sistema/financiero/
├── config/                 # Configuraciones
│   └── MongoConfig.java
├── controller/             # Controladores REST
│   ├── UsuarioController.java
│   ├── CuentaController.java
│   ├── IngresoController.java
│   ├── GastoController.java
│   └── InversionController.java
├── dto/                    # Objetos de transferencia de datos
│   ├── request/
│   │   ├── UsuarioRequest.java
│   │   ├── CuentaRequest.java
│   │   ├── IngresoRequest.java
│   │   ├── GastoRequest.java
│   │   └── InversionRequest.java
│   └── response/
│       ├── UsuarioResponse.java
│       ├── CuentaResponse.java
│       └── MovimientoResponse.java
├── enums/                  # Enumeraciones
│   ├── FrecuenciaIngreso.java
│   ├── CategoriaGasto.java
│   └── TipoInversion.java
├── exception/              # Manejo de excepciones
│   ├── GlobalExceptionHandler.java
│   └── ErrorResponse.java
├── model/                  # Modelos de entidades MongoDB
│   ├── Usuario.java
│   ├── Cuenta.java
│   ├── Ingreso.java
│   ├── Gasto.java
│   ├── Inversion.java
│   └── Evento.java
├── repository/             # Repositorios MongoDB
│   ├── UsuarioRepository.java
│   ├── CuentaRepository.java
│   ├── IngresoRepository.java
│   ├── GastoRepository.java
│   ├── InversionRepository.java
│   └── EventoRepository.java
├── service/                # Interfaces de servicios
│   ├── UsuarioService.java
│   ├── CuentaService.java
│   ├── IngresoService.java
│   ├── GastoService.java
│   └── InversionService.java
└── service/impl/           # Implementaciones de servicios
    ├── UsuarioServiceImpl.java
    ├── CuentaServiceImpl.java
    ├── IngresoServiceImpl.java
    ├── GastoServiceImpl.java
    └── InversionServiceImpl.java
```

## 🔌 Dependencias Utilizadas

- **Spring Boot Web**: Para crear aplicaciones RESTful
- **Spring Data MongoDB**: Para interacción con MongoDB
- **Lombok**: Para reducir código repetitivo
- **Spring Boot DevTools**: Para reinicios rápidos en desarrollo
- **Spring Validation**: Para validación de datos

## 🚀 Endpoints Disponibles

### Usuarios
- `POST /api/v1/api/usuarios` - Crear usuario
- `GET /api/v1/api/usuarios` - Obtener todos
- `GET /api/v1/api/usuarios/{id}` - Obtener por ID
- `GET /api/v1/api/usuarios/correo/{correo}` - Obtener por correo
- `PUT /api/v1/api/usuarios/{id}` - Actualizar usuario
- `DELETE /api/v1/api/usuarios/{id}` - Eliminar usuario

### Cuentas
- `POST /api/v1/api/cuentas` - Crear cuenta
- `GET /api/v1/api/cuentas` - Obtener todas
- `GET /api/v1/api/cuentas/{id}` - Obtener por ID
- `GET /api/v1/api/cuentas/usuario/{usuarioId}` - Obtener cuentas por usuario
- `PUT /api/v1/api/cuentas/{id}` - Actualizar cuenta
- `PATCH /api/v1/api/cuentas/{id}/saldo` - Actualizar saldo
- `DELETE /api/v1/api/cuentas/{id}` - Eliminar cuenta

### Ingresos
- `POST /api/v1/api/ingresos` - Crear ingreso
- `GET /api/v1/api/ingresos` - Obtener todos
- `GET /api/v1/api/ingresos/{id}` - Obtener por ID
- `GET /api/v1/api/ingresos/cuenta/{cuentaId}` - Obtener por cuenta
- `GET /api/v1/api/ingresos/frecuencia/{frecuencia}` - Obtener por frecuencia
- `PUT /api/v1/api/ingresos/{id}` - Actualizar ingreso
- `DELETE /api/v1/api/ingresos/{id}` - Eliminar ingreso

### Gastos
- `POST /api/v1/api/gastos` - Crear gasto
- `GET /api/v1/api/gastos` - Obtener todos
- `GET /api/v1/api/gastos/{id}` - Obtener por ID
- `GET /api/v1/api/gastos/cuenta/{cuentaId}` - Obtener por cuenta
- `GET /api/v1/api/gastos/tipo/{tipoGasto}` - Obtener por tipo
- `GET /api/v1/api/gastos/fijos` - Obtener gastos fijos
- `PUT /api/v1/api/gastos/{id}` - Actualizar gasto
- `DELETE /api/v1/api/gastos/{id}` - Eliminar gasto

### Inversiones
- `POST /api/v1/api/inversiones` - Crear inversión
- `GET /api/v1/api/inversiones` - Obtener todas
- `GET /api/v1/api/inversiones/{id}` - Obtener por ID
- `GET /api/v1/api/inversiones/cuenta/{cuentaId}` - Obtener por cuenta
- `GET /api/v1/api/inversiones/activas` - Obtener activas
- `GET /api/v1/api/inversiones/tipo/{tipoInversion}` - Obtener por tipo
- `PUT /api/v1/api/inversiones/{id}` - Actualizar inversión
- `DELETE /api/v1/api/inversiones/{id}` - Eliminar inversión

## 📊 Modelos de Datos

### Usuario
- `id` - ID único (MongoDB)
- `nombre` - Nombre del usuario
- `apellido` - Apellido del usuario
- `correo` - Email único
- `telefono` - Teléfono
- `contrasena` - Contraseña (encriptada en producción)
- `activo` - Estado del usuario

### Cuenta
- `id` - ID único
- `usuarioId` - Referencia al usuario
- `nombre` - Nombre de la cuenta
- `saldo` - Saldo actual
- `descripcion` - Descripción
- `fechaCreacion` - Fecha de creación
- `fechaActualizacion` - Última actualización
- `activa` - Estado de la cuenta

### Ingreso
- `id` - ID único
- `cuentaId` - Referencia a la cuenta
- `monto` - Monto del ingreso
- `descripcion` - Descripción
- `frecuencia` - MENSUAL, DIARIO, QUINCENAL
- `fecha` - Fecha del ingreso
- `fechaCreacion` - Fecha de creación

### Gasto
- `id` - ID único
- `cuentaId` - Referencia a la cuenta
- `monto` - Monto del gasto
- `descripcion` - Descripción
- `tipoGasto` - Categoría del gasto
- `esFijo` - Si es gasto recurrente
- `recordatorio` - Si tiene recordatorio
- `fecha` - Fecha del gasto

### Inversion
- `id` - ID único
- `cuentaId` - Referencia a la cuenta
- `monto` - Monto invertido
- `descripcion` - Descripción
- `tipoInversion` - GANADERIA, DEPORTE, CDT, VIVIENDA, EMPRESA
- `estaActiva` - Estado de la inversión
- `rentabilidadEsperada` - Porcentaje esperado
- `fecha` - Fecha de inversión
- `fechaVencimiento` - Fecha de vencimiento

## 📝 Validaciones

Todas las validaciones se realizan usando `javax.validation`:
- `@NotBlank` - Campo no puede estar vacío
- `@NotNull` - Campo no puede ser nulo
- `@Positive` - Valor debe ser positivo
- `@Email` - Debe ser un email válido

## ⚙️ Configuración MongoDB

En `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/financiero
spring.data.mongodb.auto-index-creation=true
```

## 🔧 Compilación y Ejecución

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar
mvn package

# Ejecutar aplicación
mvn spring-boot:run

# O directamente
java -jar target/financiero-0.0.1-SNAPSHOT.jar
```

## 📱 Ejemplo de Requests

### Crear Usuario
```json
POST /api/v1/api/usuarios
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "correo": "juan@example.com",
  "telefono": "3105555555",
  "contrasena": "password123"
}
```

### Crear Cuenta
```json
POST /api/v1/api/cuentas
{
  "usuarioId": "user_id_aqui",
  "nombre": "Mi Cuenta Ahorros",
  "saldo": 1000000,
  "descripcion": "Cuenta de ahorros principal"
}
```

### Crear Gasto
```json
POST /api/v1/api/gastos
{
  "cuentaId": "cuenta_id_aqui",
  "monto": 50000,
  "descripcion": "Compra en supermercado",
  "tipoGasto": "ALIMENTACION",
  "esFijo": false,
  "recordatorio": true
}
```

## 🛡️ Manejo de Errores

El sistema incluye un manejador global de excepciones que devuelve respuestas estructuradas:

```json
{
  "timestamp": "2026-06-03T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuario no encontrado",
  "path": "/api/v1/api/usuarios/invalid-id"
}
```

## 📚 Próximas Fases

1. **Autenticación y Seguridad** - Spring Security + JWT
2. **Reportes y Analytics** - Generación de reportes financieros
3. **Notificaciones** - Email y SMS
4. **Front-end** - Angular o React
5. **Docker** - Containerización de la aplicación

---

**Versión:** 0.0.1  
**Última actualización:** 3 de Junio de 2026

