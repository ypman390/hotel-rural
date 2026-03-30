package com.acostaivan.hotel_rural.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL      = "jdbc:mariadb://localhost:3306/hotel_rural";
    private static final String USUARIO  = "usuarioHotelRural";
    private static final String PASSWORD = "1234";

    private ConexionBD() {}

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public static void cerrar(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}