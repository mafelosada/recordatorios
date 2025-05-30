package com.recordatoriosMedicamentos.recordatorios.DTO;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;

public class Paciente_medicamentoDTO {
    @JsonProperty("id")
    private int pacientes_medicamentosID;
    private Pacientes paciente;
    private Medicamento medicamento;
    private String dosis;
    private Time horario;  

    public Paciente_medicamentoDTO() {
    }

    public Paciente_medicamentoDTO(int pacientes_medicamentosID, Pacientes paciente, Medicamento medicamento, String dosis, Time horario) {
        this.pacientes_medicamentosID = pacientes_medicamentosID;
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.horario = horario;
    }

    public int getPacientes_medicamentosID() {
        return pacientes_medicamentosID;
    }
    public void setPacientes_medicamentosID(int pacientes_medicamentosID) {
        this.pacientes_medicamentosID = pacientes_medicamentosID;
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
