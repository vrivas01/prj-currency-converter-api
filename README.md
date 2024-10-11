# Currency Converter API

Este proyecto es un servicio web reactivo para la conversión de divisas, construido utilizando **Spring WebFlux**, **R2DBC** con la base de datos en memoria H2, e incluye integración con APIs externas de tipos de cambio. El servicio proporciona funcionalidad para convertir monedas y obtener el historial de tipos de cambio.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Primeros Pasos](#primeros-pasos)
- [Uso de Docker](#uso-de-docker)
- [Swagger](#swagger)

## Características

- Conversión de divisas utilizando APIs externas.
- Almacena el historial de conversiones en una base de datos en memoria (H2).
- Arquitectura reactiva y no bloqueante usando **Spring WebFlux**.
- Documentación del API usando **SpringDoc OpenAPI**.
- Manejo de excepciones y validación personalizada.
- Configurable a través de **Spring Cloud Config**.

## Tecnologías

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring WebFlux**: Programación reactiva con IO no bloqueante.
- **R2DBC con H2**: Conectividad de base de datos reactiva.
- **Lombok**: Simplifica el código Java reduciendo el código boilerplate.
- **OpenAPI**: Proporciona documentación del API y una interfaz Swagger UI.
- **Spring Cloud Config**: Soporte de configuración externa para sistemas distribuidos.

## Primeros Pasos

### Prerrequisitos

- Java 17 o superior.
- Maven 3.8.x o superior.

### Clonar el repositorio

```bash
git clone https://github.com/vrivas/prj-currency-converter-api.git
cd prj-currency-converter-api
```

## Uso de Docker
**Construcción de la imagen:**

```bash
docker build -t currency-converter-api .
```

**Ejecución del contenedor:**

```bash
docker run --name converter-api-container -p 8111:8111 -e "SPRING_PROFILES_ACTIVE=dev" currency-converter-api
```

## Swagger

http://localhost:8111/webjars/swagger-ui/index.html


