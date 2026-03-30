package com.acostaivan.hotel_rural.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private int edad;
    private BigDecimal saldo;
    private LocalDate fechaRegistro;
    private boolean activo;

    public Usuario() {}

    public Usuario(int id, String nombre, String email, String password,
                   String rol, int edad, BigDecimal saldo,
                   LocalDate fechaRegistro, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.edad = edad;
        this.saldo = saldo;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + "', email='" + email +
                "', rol='" + rol + "', activo=" + activo + "}";
    }
}