package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.Reserva;
import java.util.List;

public interface ReservaDAO {

    void insertar(Reserva reserva);

    Reserva buscarPorId(int id);
    List<Reserva> listarTodas();
    List<Reserva> listarPorUsuario(int usuarioId);

    void actualizar(Reserva reserva);

    void eliminar(int id);
}