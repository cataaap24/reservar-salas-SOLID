# Reserva de salas

Proyecto básico funcional para reservar salas en Unillanos. 

## Caso de estudio

Una universidad necesita reservar salas para actividades académicas.

El sistema permite:

- Registrar salas.
- Crear reservas.
- Consultar una reserva por ID.
- Cancelar reservas.
- Listar salas.
- Listar reservas.
- Generar un reporte.

## Tipos de sala

El código inicial maneja tres tipos de sala:

- AULA
- LABORATORIO
- AUDITORIO

## Reglas de negocio incluidas

- No se puede crear una reserva si la sala no existe.
- No se puede reservar una sala con más asistentes que su capacidad.
- No se puede reservar una sala en horario inválido.
- No se puede reservar una sala si ya tiene una reserva activa en el mismo horario.
- Un laboratorio solo se reserva para actividades de tipo PRACTICA.
- Un auditorio requiere mínimo 30 asistentes.
- Una sala cancelada no debe contarse como reserva activa.

## Requisitos

- JDK 21.
- Maven.
- IntelliJ IDEA.
- Conexión a internet para descargar dependencias Maven.

## Cómo ejecutar en IntelliJ IDEA

Este proyecto incluye una configuración en:

```text
.idea/runConfigurations/Ejecutar_JavaFX_Maven.xml
```

Al abrir el proyecto en IntelliJ IDEA, debería aparecer en la parte superior una configuración llamada:

```text
Ejecutar JavaFX Maven
```

Ejecute esa configuración.

También puede ejecutarse desde la ventana Maven:

```text
Plugins > javafx > javafx:run
```

O desde terminal:

```bash
mvn clean javafx:run
```

## Problemas de diseño

Este proyecto tiene problemas intencionales para refactorizar:

- `ReservaService` tiene demasiadas responsabilidades.
- `ReservaView` depende directamente de `ReservaService`.
- No existen repositorios como interfaces.
- Los datos se almacenan directamente en listas dentro del servicio.
- `Sala` usa un atributo `tipo` en lugar de una jerarquía o estrategia.
- Hay muchos condicionales para diferenciar tipos de sala.
- Las reglas de negocio están mezcladas dentro del servicio.
- El servicio devuelve textos directamente para la interfaz.
- Es difícil probar las reglas sin abrir JavaFX.
- No hay casos de uso separados.
- No hay DTO tipo `OperationResult`.

## Reto para los estudiantes

Refactorizar el proyecto aplicando SOLID.

Una posible estructura final podría ser:

```text
src/main/java/edu/usta/reservas/
  adapters/
    javafx/
      MainApp.java
      ReservaView.java

  entities/
    Sala.java
    Aula.java
    Laboratorio.java
    Auditorio.java
    Reserva.java

  usecases/
    RegistrarSalaUseCase.java
    CrearReservaUseCase.java
    ConsultarReservaUseCase.java
    CancelarReservaUseCase.java
    ListarSalasUseCase.java
    ListarReservasUseCase.java

  usecases/dto/
    OperationResult.java

  usecases/ports/
    SalaRepository.java
    ReservaRepository.java

  usecases/rules/
    ReglaReserva.java
    ReglaCapacidad.java
    ReglaDisponibilidad.java
    ReglaHorarioInstitucional.java
    ReglaLaboratorio.java
    ReglaAuditorio.java

  infrastructure/
    repositories/
      InMemorySalaRepository.java
      InMemoryReservaRepository.java
```

## Actividades sugeridas

1. Ejecutar el proyecto original.
2. Identificar responsabilidades mezcladas.
3. Relacionar problemas con SRP, OCP, LSP, ISP y DIP.
4. Refactorizar gradualmente.
5. Crear pruebas unitarias para las reglas de reserva.
6. Elaborar un diagrama de clases antes y después.
7. Explicar qué principio SOLID se aplicó en cada cambio.
