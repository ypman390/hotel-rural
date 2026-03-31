package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.ServicioExtra;
import java.util.List;

public interface ServicioExtraDAO {

    void insertar(ServicioExtra servicio);

    ServicioExtra buscarPorId(int id);
    List<ServicioExtra> listarTodos();
    List<ServicioExtra> listarActivos();

    void actualizar(ServicioExtra servicio);

    void eliminar(int id);
}