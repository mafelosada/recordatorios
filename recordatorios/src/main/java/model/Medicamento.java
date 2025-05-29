package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicamentosID")
    private int medicamentosID;

    @Column(name="nombreMedicamentos", length=100, nullable=false)
    private String nombreMedicamentos;

    public Medicamento() {
    }

    public Medicamento(int medicamentosID, String nombreMedicamentos) {
        this.medicamentosID = medicamentosID;
        this.nombreMedicamentos = nombreMedicamentos;
    }

    public int getMedicamentosID() {
        return medicamentosID;
    }
    public void setMedicamentosID(int medicamentosID) {
        this.medicamentosID = medicamentosID;
    }

    public String getNombreMedicamentos() {
        return nombreMedicamentos;
    }
    public void setNombreMedicamentos(String nombreMedicamentos) {
        this.nombreMedicamentos = nombreMedicamentos;
    }

}
