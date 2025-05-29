package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicamentoDTO {

     @JsonProperty("id")
    private int medicamentosID;
    private String nombreMedicamentos;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(int medicamentosID, String nombreMedicamentos) {
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
