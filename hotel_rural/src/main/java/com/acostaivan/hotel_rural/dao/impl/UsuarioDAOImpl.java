package com.acostaivan.hotel_rural.dao.impl;

import com.acostaivan.hotel_rural.dao.UsuarioDAO;
import com.acostaivan.hotel_rural.modelo.Usuario;
import com.acostaivan.hotel_rural.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void insertar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, email, password, rol, edad, saldo, fecha_registro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getEdad());
            ps.setBigDecimal(6, u.getSaldo());
            ps.setDate(7, Date.valueOf(u.getFechaRegistro()));
            ps.setBoolean(8, u.isActivo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Usuario> listarActivos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE activo = TRUE";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios activos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, email=?, password=?, rol=?, edad=?, saldo=?, fecha_registro=?, activo=? WHERE id=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRol());
            ps.setInt(5, u.getEdad());
            ps.setBigDecimal(6, u.getSaldo());
            ps.setDate(7, Date.valueOf(u.getFechaRegistro()));
            ps.setBoolean(8, u.isActivo());
            ps.setInt(9, u.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario login(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ? AND activo = TRUE";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean existeEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar email: " + e.getMessage());
        }
        return false;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRol(rs.getString("rol"));
        u.setEdad(rs.getInt("edad"));
        u.setSaldo(rs.getBigDecimal("saldo"));
        u.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }
}