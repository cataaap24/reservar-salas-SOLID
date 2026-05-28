package com.reserva.usecases.services;

import com.reserva.entities.Sala;
import com.reserva.entities.*;
import com.reserva.usecases.dto.OperationResult;
import com.reserva.usecases.factories.SalaFactory;
import com.reserva.usecases.ports.SalaRepository;
import com.reserva.usecases.rules.sala.ReglaSala;
import com.reserva.usecases.rules.sala.ReglaTipo;

import java.util.List;

public class RegistrarSalaUseCase {
     private final SalaRepository salaRepository;
     private final List<ReglaSala> reglasSalas;

     public RegistrarSalaUseCase(SalaRepository salaRepository, List<ReglaSala> reglasSalas) {
         this.salaRepository = salaRepository;
         this.reglasSalas = reglasSalas;
     }

     public OperationResult ejecutar(String id, String nombre, String tipo, int capacidad, String ubicacion) {

         //Verificar campos de entrada

         if (id == null || id.isEmpty()) return OperationResult.fail("El id de la sala no puede estar vacío");

         if (nombre == null || nombre.isBlank()) return OperationResult.fail("El nombre de la sala no puede estar vacío");

         if (tipo == null || tipo.isBlank()) return OperationResult.fail("El tipo de la sala no puede estar vacío");

         if (capacidad <= 0) return OperationResult.fail("La capacidad debe ser mayor a 0");

         //Verificar regla del tipo para las salas

         OperationResult resultadoTipo = new ReglaTipo().validar(tipo);
         if (!resultadoTipo.isSuccess()) {
             return resultadoTipo;
         }

         //Crear objeto dependiendo del tipo {SalaFactory para evitar switch}

         Sala sala = SalaFactory.crear(id, nombre, tipo, capacidad, ubicacion);

         //Verificar cada regla

         for (ReglaSala reglaSala : reglasSalas) {
             OperationResult result = reglaSala.validar(sala);
             if (!result.isSuccess()) {
                 return result;
             }
         }

         //Generar persistencia

         salaRepository.guardar(sala);

         return OperationResult.ok("Sala registrada exitosamente");
     }
}
