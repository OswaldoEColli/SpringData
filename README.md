# Sistema de Transferencias Bancarias

Sistema simple para realizar transferencias entre cuentas bancarias usando Spring Boot.

## Características

- Modelo de Cuenta con ID, propietario y saldo
- Servicio de transferencia entre cuentas
- Repositorio para operaciones CRUD
- API REST para realizar transferencias
- Manejo de excepciones personalizado
- Documentación con OpenAPI

## Requisitos

- Java 17+
- Maven 3.6+

## Ejecución

1. Clonar el repositorio
2. Ejecutar `mvn spring-boot:run`
3. Acceder a la API en http://localhost:8080
4. Documentación en http://localhost:8080/swagger-ui.html
5. Consola H2 en http://localhost:8080/h2-console

## Endpoints

- POST /api/transferencias - Realizar una transferencia
- GET /api/transferencias/{id}/saldo - Consultar saldo de una cuenta