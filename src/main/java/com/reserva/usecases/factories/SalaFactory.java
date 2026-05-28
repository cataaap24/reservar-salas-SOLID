package com.reserva.usecases.factories;

import com.reserva.entities.Auditorio;
import com.reserva.entities.Aula;
import com.reserva.entities.Laboratorio;
import com.reserva.entities.Sala;

public class SalaFactory {
    public static Sala crear(String id, String nombre, String tipo, int capacidad, String ubicacion) {
        return switch (tipo.toUpperCase().trim()) {
            case "AULA" -> new Aula(id, nombre, capacidad, ubicacion);
            case "LABORATORIO" -> new Laboratorio(id, nombre, capacidad, ubicacion);
            case "AUDITORIO" ->  new Auditorio(id, nombre, capacidad, ubicacion);
            default -> throw new IllegalStateException("Tipo invalido");
        };
    }
}
