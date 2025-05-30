package com.recordatoriosMedicamentos.recordatorios.DTO;

import java.util.Date;

import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;

public class BitacorasDTO {
     
    private int bitacoraID;
    private Pacientes paciente;
    private Medicamento medicamento;
    private Date fecha;

    public BitacorasDTO() {
    }

    public BitacorasDTO(int bitacoraID, Pacientes paciente, Medicamento medicamento, Date fecha) {
        this.bitacoraID = bitacoraID;
        this.paciente = paciente;
        this.medicamento = medicamento;
        this.fecha = fecha;
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
