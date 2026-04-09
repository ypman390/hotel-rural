package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.Habitacion;

import java.math.BigDecimal;
import java.util.List;

public interface HabitacionDAO {

    void insertar(Habitacion habitacion);

    Habitacion buscarPorId(int id);
    List<Habitacion> listarTodas();
    List<Habitacion> listarDisponibles();
    List<Habitacion> buscar(String nombre, BigDecimal precioMax, Integer capacidad, Boolean disponible);

    void cambiarDisponibilidad(int id, boolean disponible);

    void actualizar(Habitacion habitacion);

    void eliminar(int id);

}