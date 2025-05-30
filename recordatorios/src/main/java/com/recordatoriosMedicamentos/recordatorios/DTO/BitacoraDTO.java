package com.recordatoriosMedicamentos.recordatorios.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BitacoraDTO {

    @JsonProperty("id")
    private int bitacoraID;

    private int pacienteID;
    private int medicamentoID;
    private Date fecha;

    public BitacoraDTO() {
    }

    public BitacoraDTO(int bitacoraID, int pacienteID, int medicamentoID, Date fecha) {
        this.bitacoraID = bitacoraID;
        this.pacienteID = pacienteID;
        this.medicamentoID = medicamentoID;
        this.fecha = fecha;
    }

    public int getBitacoraID() {
        return bitacoraID;
    }

    public void setBitacoraID(int bitacoraID) {
        this.bitacoraID = bitacoraID;
    }

    public int getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(int pacienteID) {
        this.pacienteID = pacienteID;
    }

    public int getMedicamentoID() {
        return medicamentoID;
    }

    public void setMedicamentoID(int medicamentoID) {
        this.medicamentoID = medicamentoID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
