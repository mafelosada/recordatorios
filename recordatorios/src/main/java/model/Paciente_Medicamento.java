package model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "pacientesMedicamentos")
public class Paciente_Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pacientes_medicamentos")
    private int pacientes_medicamentosID;

    @ManyToOne
    @JoinColumn(name = "pacientesID", nullable = false)
    private Pacientes paciente;

    @ManyToOne
    @JoinColumn(name = "medicamentosID", nullable = false)
    private Medicamento medicamento;

    @Column(name = "dosis", length = 50, nullable = false)
    private String dosis;

    @Column(name = "horario", nullable = false)
    private Time horario;  

    public Paciente_Medicamento() {
    }

    public Paciente_Medicamento(int pacientes_medicamentosID, Pacientes paciente, Medicamento medicamento, String dosis, Time horario) {
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
