package com.acostaivan.hotel_rural.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {

    public static void main(String[] args) {
        System.out.println("Probando conexión a MariaDB...");

        try (Connection con = ConexionBD.getConexion()) {
            if (con != null) {
                System.out.println("Conexión exitosa a la base de datos hotel_rural");
                System.out.println("   Driver: " + con.getMetaData().getDriverName());
                System.out.println("   URL:    " + con.getMetaData().getURL());
            }
        } catch (SQLException e) {
            System.err.println(" Error al conectar: " + e.getMessage());
        }
    }
}