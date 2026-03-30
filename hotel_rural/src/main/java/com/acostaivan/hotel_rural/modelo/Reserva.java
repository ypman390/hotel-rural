package com.acostaivan.hotel_rural.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {

    private int id;
    private int usuarioId;
    private int habitacionId;
    private int numeroHuespedes;
    private BigDecimal precioTotal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean confirmada;
    private String observaciones;

    public Reserva() {}

    public Reserva(int id, int usuarioId, int habitacionId, int numeroHuespedes,
                   BigDecimal precioTotal, LocalDate fechaInicio, LocalDate fechaFin,
                   boolean confirmada, String observaciones) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.habitacionId = habitacionId;
        this.numeroHuespedes = numeroHuespedes;
        this.precioTotal = precioTotal;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.confirmada = confirmada;
        this.observaciones = observaciones;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getHabitacionId() { return habitacionId; }
    public void setHabitacionId(int habitacionId) { this.habitacionId = habitacionId; }

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

    @Override
    public String toString() {
        return "Reserva{id=" + id + ", usuarioId=" + usuarioId + ", habitacionId=" +
                habitacionId + ", fechaInicio=" + fechaInicio + ", fechaFin=" +
                fechaFin + ", confirmada=" + confirmada + "}";
    }
}