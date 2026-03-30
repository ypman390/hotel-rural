package com.acostaivan.hotel_rural.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicioExtra {

    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int duracionMinutos;
    private LocalDate fechaCreacion;
    private boolean activo;

    public ServicioExtra() {}

    public ServicioExtra(int id, String nombre, String descripcion, BigDecimal precio,
                         int duracionMinutos, LocalDate fechaCreacion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.duracionMinutos = duracionMinutos;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "ServicioExtra{id=" + id + ", nombre='" + nombre + "', precio=" +
                precio + ", duracionMinutos=" + duracionMinutos + ", activo=" + activo + "}";
    }
}