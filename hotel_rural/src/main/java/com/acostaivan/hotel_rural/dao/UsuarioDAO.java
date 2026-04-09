package com.acostaivan.hotel_rural.dao;

import com.acostaivan.hotel_rural.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {

    // Crear
    void insertar(Usuario usuario);

    // Leer
    Usuario buscarPorId(int id);
    Usuario buscarPorEmail(String email);
    List<Usuario> listarTodos();
    List<Usuario> listarActivos();
    List<Usuario> buscar(String nombre, String rol, Boolean activo);

    // Actualizar
    void actualizar(Usuario usuario);

    // Eliminar
    void eliminar(int id);

    // Login
    Usuario login(String email, String password);

    // Verificar si existe
    boolean existeEmail(String email);
}