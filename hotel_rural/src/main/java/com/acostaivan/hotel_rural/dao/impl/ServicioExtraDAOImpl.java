package com.acostaivan.hotel_rural.dao.impl;

import com.acostaivan.hotel_rural.dao.ServicioExtraDAO;
import com.acostaivan.hotel_rural.modelo.ServicioExtra;
import com.acostaivan.hotel_rural.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioExtraDAOImpl implements ServicioExtraDAO {

    @Override
    public void insertar(ServicioExtra s) {
        String sql = "INSERT INTO servicios_extra (nombre, descripcion, precio, duracion_minutos, fecha_creacion, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDescripcion());
            ps.setBigDecimal(3, s.getPrecio());
            ps.setInt(4, s.getDuracionMinutos());
            ps.setDate(5, Date.valueOf(s.getFechaCreacion()));
            ps.setBoolean(6, s.isActivo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar servicio extra: " + e.getMessage());
        }
    }

    @Override
    public ServicioExtra buscarPorId(int id) {
        String sql = "SELECT * FROM servicios_extra WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar servicio extra: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ServicioExtra> listarTodos() {
        List<ServicioExtra> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios_extra";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar servicios extra: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<ServicioExtra> listarActivos() {
        List<ServicioExtra> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios_extra WHERE activo = TRUE";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar servicios extra activos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(ServicioExtra s) {
        String sql = "UPDATE servicios_extra SET nombre=?, descripcion=?, precio=?, duracion_minutos=?, fecha_creacion=?, activo=? WHERE id=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDescripcion());
            ps.setBigDecimal(3, s.getPrecio());
            ps.setInt(4, s.getDuracionMinutos());
            ps.setDate(5, Date.valueOf(s.getFechaCreacion()));
            ps.setBoolean(6, s.isActivo());
            ps.setInt(7, s.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio extra: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM servicios_extra WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio extra: " + e.getMessage());
        }
    }

    private ServicioExtra mapear(ResultSet rs) throws SQLException {
        ServicioExtra s = new ServicioExtra();
        s.setId(rs.getInt("id"));
        s.setNombre(rs.getString("nombre"));
        s.setDescripcion(rs.getString("descripcion"));
        s.setPrecio(rs.getBigDecimal("precio"));
        s.setDuracionMinutos(rs.getInt("duracion_minutos"));
        s.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
        s.setActivo(rs.getBoolean("activo"));
        return s;
    }
}