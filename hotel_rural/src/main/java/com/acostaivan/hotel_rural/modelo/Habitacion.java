package com.acostaivan.hotel_rural.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Habitacion {

    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioNoche;
    private int capacidad;
    private LocalDate fechaAlta;
    private boolean disponible;
    private String imagen;
    private BigDecimal valoracion;

    public Habitacion() {}

    public Habitacion(int id, String nombre, String descripcion, BigDecimal precioNoche,
                      int capacidad, LocalDate fechaAlta, boolean disponible,
                      String imagen, BigDecimal valoracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioNoche = precioNoche;
        this.capacidad = capacidad;
        this.fechaAlta = fechaAlta;
        this.disponible = disponible;
        this.imagen = imagen;
        this.valoracion = valoracion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(BigDecimal precioNoche) { this.precioNoche = precioNoche; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public BigDecimal getValoracion() { return valoracion; }
    public void setValoracion(BigDecimal valoracion) { this.valoracion = valoracion; }

    @Override
    public String toString() {
        return "Habitacion{id=" + id + ", nombre='" + nombre + "', precioNoche=" +
                precioNoche + ", capacidad=" + capacidad + ", disponible=" + disponible + "}";
    }
}