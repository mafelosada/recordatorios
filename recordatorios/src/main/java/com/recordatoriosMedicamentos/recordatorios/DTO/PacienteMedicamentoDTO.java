package com.recordatoriosMedicamentos.recordatorios.DTO;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PacienteMedicamentoDTO {

    @JsonProperty("id")
    private int pacientesMedicamentosID;

    @JsonProperty("pacienteId")
    private int pacienteId;

    @JsonProperty("medicamentoId")
    private int medicamentoId;

    private String dosis;

    private Time horario;

    public PacienteMedicamentoDTO() {
    }

    public PacienteMedicamentoDTO(int pacientesMedicamentosID, int pacienteId, int medicamentoId, String dosis, Time horario) {
        this.pacientesMedicamentosID = pacientesMedicamentosID;
        this.pacienteId = pacienteId;
        this.medicamentoId = medicamentoId;
        this.dosis = dosis;
        this.horario = horario;
    }

    // Getters y setters...
    public int getPacientesMedicamentosID() {
        return pacientesMedicamentosID;
    }

    public void setPacientesMedicamentosID(int pacientesMedicamentosID) {
        this.pacientesMedicamentosID = pacientesMedicamentosID;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }
}