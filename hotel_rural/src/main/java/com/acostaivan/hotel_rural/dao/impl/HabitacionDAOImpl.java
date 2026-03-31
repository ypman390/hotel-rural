package com.acostaivan.hotel_rural.dao.impl;

import com.acostaivan.hotel_rural.dao.HabitacionDAO;
import com.acostaivan.hotel_rural.modelo.Habitacion;
import com.acostaivan.hotel_rural.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO {

    @Override
    public void insertar(Habitacion h) {
        String sql = "INSERT INTO habitaciones (nombre, descripcion, precio_noche, capacidad, fecha_alta, disponible, imagen, valoracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getNombre());
            ps.setString(2, h.getDescripcion());
            ps.setBigDecimal(3, h.getPrecioNoche());
            ps.setInt(4, h.getCapacidad());
            ps.setDate(5, Date.valueOf(h.getFechaAlta()));
            ps.setBoolean(6, h.isDisponible());
            ps.setString(7, h.getImagen());
            ps.setBigDecimal(8, h.getValoracion());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar habitación: " + e.getMessage());
        }
    }

    @Override
    public Habitacion buscarPorId(int id) {
        String sql = "SELECT * FROM habitaciones WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar habitación: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Habitacion> listarTodas() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar habitaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Habitacion> listarDisponibles() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones WHERE disponible = TRUE";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar habitaciones disponibles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Habitacion h) {
        String sql = "UPDATE habitaciones SET nombre=?, descripcion=?, precio_noche=?, capacidad=?, fecha_alta=?, disponible=?, imagen=?, valoracion=? WHERE id=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getNombre());
            ps.setString(2, h.getDescripcion());
            ps.setBigDecimal(3, h.getPrecioNoche());
            ps.setInt(4, h.getCapacidad());
            ps.setDate(5, Date.valueOf(h.getFechaAlta()));
            ps.setBoolean(6, h.isDisponible());
            ps.setString(7, h.getImagen());
            ps.setBigDecimal(8, h.getValoracion());
            ps.setInt(9, h.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM habitaciones WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar habitación: " + e.getMessage());
        }
    }

    private Habitacion mapear(ResultSet rs) throws SQLException {
        Habitacion h = new Habitacion();
        h.setId(rs.getInt("id"));
        h.setNombre(rs.getString("nombre"));
        h.setDescripcion(rs.getString("descripcion"));
        h.setPrecioNoche(rs.getBigDecimal("precio_noche"));
        h.setCapacidad(rs.getInt("capacidad"));
        h.setFechaAlta(rs.getDate("fecha_alta").toLocalDate());
        h.setDisponible(rs.getBoolean("disponible"));
        h.setImagen(rs.getString("imagen"));
        h.setValoracion(rs.getBigDecimal("valoracion"));
        return h;
    }
}