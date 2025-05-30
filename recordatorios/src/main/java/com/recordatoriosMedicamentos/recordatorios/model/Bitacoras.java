package com.recordatoriosMedicamentos.recordatorios.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bitacora")
public class Bitacoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bitacoraID")
    private int bitacoraID;

    @ManyToOne
    @JoinColumn(name = "pacientesID", nullable = false)
    private Pacientes paciente;

    @ManyToOne
    @JoinColumn(name = "medicamentosID", nullable = false)
    private Medicamento medicamento;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public Bitacoras() {
    }

    public Bitacoras(int bitacoraID, Pacientes paciente, Medicamento medicamento, String fecha) {
        this.bitacoraID = bitacoraID;
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.fecha = java.sql.Date.valueOf(fecha);
    }

    public int getBitacoraID() {
        return bitacoraID;
    }
    public void setBitacoraID(int bitacoraID) {
        this.bitacoraID = bitacoraID;
    }

    public Pacientes getPaciente() {
        return paciente;
    }
    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
