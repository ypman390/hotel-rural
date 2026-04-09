package com.acostaivan.hotel_rural.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaDetalle {

    private int id;
    private String nombreUsuario;
    private String nombreHabitacion;
    private int numeroHuespedes;
    private BigDecimal precioTotal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean confirmada;
    private String observaciones;
    private int usuarioId;
    private int habitacionId;

    public ReservaDetalle() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getNombreHabitacion() { return nombreHabitacion; }
    public void setNombreHabitacion(String nombreHabitacion) { this.nombreHabitacion = nombreHabitacion; }

    public int getNumeroHuespedes() { return numeroHuespedes; }
    public void setNumeroHuespedes(int numeroHuespedes) { this.numeroHuespedes = numeroHuespedes; }

    public BigDecimal getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getHabitacionId() { return habitacionId; }
    public void setHabitacionId(int habitacionId) { this.habitacionId = habitacionId; }
}