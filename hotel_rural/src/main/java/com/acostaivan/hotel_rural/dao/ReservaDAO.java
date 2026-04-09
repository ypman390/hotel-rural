package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.Reserva;
import com.acostaivan.hotel_rural.modelo.ReservaDetalle;

import java.time.LocalDate;
import java.util.List;

public interface ReservaDAO {

    void insertar(Reserva reserva);

    Reserva buscarPorId(int id);
    List<Reserva> listarTodas();
    List<Reserva> listarPorUsuario(int usuarioId);
    List<ReservaDetalle> buscar(Integer usuarioId, Integer habitacionId, Boolean confirmada, LocalDate fechaInicio, LocalDate fechaFin);

    void actualizar(Reserva reserva);

    void eliminar(int id);

    List<ReservaDetalle> listarConDetalle();
    List<ReservaDetalle> listarConDetallePorUsuario(int usuarioId);
}