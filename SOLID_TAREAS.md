# Guía de refactorización SOLID

## 1. Diagnóstico inicial

Revise las clases:

- `MainApp`
- `ReservaView`
- `ReservaService`
- `Sala`
- `Reserva`

Responda:

1. ¿Qué responsabilidades tiene `ReservaService`?
2. ¿Qué responsabilidades tiene `ReservaView`?
3. ¿Qué responsabilidades tiene `Sala`?
4. ¿Qué cambios habría que hacer si aparece un nuevo tipo de sala, por ejemplo `SALA_HIBRIDA`?
5. ¿Qué cambios habría que hacer si las reservas se almacenan en base de datos?
6. ¿Es fácil probar la regla de disponibilidad sin ejecutar JavaFX?
7. ¿Dónde están mezcladas las reglas de negocio con la presentación?

## 2. Problemas intencionales asociados con SOLID

### SRP

`ReservaService` registra salas, crea reservas, cancela reservas, busca datos, valida reglas, genera reportes y almacena listas.

### OCP

Las reglas dependen de condicionales:

```java
if (sala.getTipo().equals("LABORATORIO")) {
    ...
}

if (sala.getTipo().equals("AUDITORIO")) {
    ...
}
```

Si aparece un nuevo tipo de sala, se modifican métodos ya existentes.

### LSP

El sistema no tiene una jerarquía clara de salas. Todas las salas se representan igual, aunque no todas tienen las mismas reglas.

### ISP

No existen interfaces. Al refactorizar, se debe evitar crear interfaces demasiado grandes.

### DIP

La vista y la lógica dependen de clases concretas. No hay abstracciones como `SalaRepository`, `ReservaRepository` o `ReglaReserva`.

## 3. Refactorización sugerida

Avance por etapas.

### Etapa 1: separar entidades

- `Sala`
- `Aula`
- `Laboratorio`
- `Auditorio`
- `Reserva`

### Etapa 2: crear DTO de resultado

- `OperationResult`

### Etapa 3: crear puertos

- `SalaRepository`
- `ReservaRepository`

### Etapa 4: crear casos de uso

- `RegistrarSalaUseCase`
- `CrearReservaUseCase`
- `ConsultarReservaUseCase`
- `CancelarReservaUseCase`

### Etapa 5: separar reglas de reserva

Crear una interfaz:

```java
public interface ReglaReserva {
    OperationResult validar(Reserva reserva, Sala sala);
}
```

Luego crear reglas específicas:

- `ReglaCapacidad`
- `ReglaDisponibilidad`
- `ReglaHorarioInstitucional`
- `ReglaLaboratorio`
- `ReglaAuditorio`

### Etapa 6: adaptar JavaFX

La interfaz JavaFX no debe contener reglas de negocio. Solo debe capturar datos, llamar casos de uso o una fachada, y mostrar resultados.

## 4. Entrega sugerida

1. Código refactorizado.
2. Diagrama de clases inicial.
3. Diagrama de clases refactorizado.
4. Pruebas unitarias.
5. Documento breve explicando los principios SOLID aplicados.
