package com.acostaivan.hotel_rural.dao.impl;

import com.acostaivan.hotel_rural.dao.ReservaDAO;
import com.acostaivan.hotel_rural.modelo.Reserva;
import com.acostaivan.hotel_rural.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    @Override
    public void insertar(Reserva r) {
        String sql = "INSERT INTO reservas (usuario_id, habitacion_id, numero_huespedes, precio_total, fecha_inicio, fecha_fin, confirmada, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getUsuarioId());
            ps.setInt(2, r.getHabitacionId());
            ps.setInt(3, r.getNumeroHuespedes());
            ps.setBigDecimal(4, r.getPrecioTotal());
            ps.setDate(5, Date.valueOf(r.getFechaInicio()));
            ps.setDate(6, Date.valueOf(r.getFechaFin()));
            ps.setBoolean(7, r.isConfirmada());
            ps.setString(8, r.getObservaciones());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar reserva: " + e.getMessage());
        }
    }

    @Override
    public Reserva buscarPorId(int id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar reserva: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reserva> listarTodas() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reservas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Reserva> listarPorUsuario(int usuarioId) {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE usuario_id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reservas por usuario: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Reserva r) {
        String sql = "UPDATE reservas SET usuario_id=?, habitacion_id=?, numero_huespedes=?, precio_total=?, fecha_inicio=?, fecha_fin=?, confirmada=?, observaciones=? WHERE id=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getUsuarioId());
            ps.setInt(2, r.getHabitacionId());
            ps.setInt(3, r.getNumeroHuespedes());
            ps.setBigDecimal(4, r.getPrecioTotal());
            ps.setDate(5, Date.valueOf(r.getFechaInicio()));
            ps.setDate(6, Date.valueOf(r.getFechaFin()));
            ps.setBoolean(7, r.isConfirmada());
            ps.setString(8, r.getObservaciones());
            ps.setInt(9, r.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
        }
    }

    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setId(rs.getInt("id"));
        r.setUsuarioId(rs.getInt("usuario_id"));
        r.setHabitacionId(rs.getInt("habitacion_id"));
        r.setNumeroHuespedes(rs.getInt("numero_huespedes"));
        r.setPrecioTotal(rs.getBigDecimal("precio_total"));
        r.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        r.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        r.setConfirmada(rs.getBoolean("confirmada"));
        r.setObservaciones(rs.getString("observaciones"));
        return r;
    }
}