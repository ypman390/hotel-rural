package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.Habitacion;
import java.util.List;

public interface HabitacionDAO {

    void insertar(Habitacion habitacion);

    Habitacion buscarPorId(int id);
    List<Habitacion> listarTodas();
    List<Habitacion> listarDisponibles();

    void actualizar(Habitacion habitacion);

    void eliminar(int id);
}