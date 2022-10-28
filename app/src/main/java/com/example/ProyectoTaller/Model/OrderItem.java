package com.example.ProyectoTaller.Model;

public class OrderItem {
    String nombre = "";
    String equipo = "";
    String estado = "";
    String numeroOrden = "";
    String observaciones = "";
    String tecnico = "";

    public OrderItem(String nombre, String equipo, String estado, String numeroOrden, String observaciones, String tecnico) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.estado = estado;
        this.numeroOrden = numeroOrden;
        this.observaciones = observaciones;
        this.tecnico = tecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }
}
