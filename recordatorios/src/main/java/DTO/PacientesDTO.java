package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PacientesDTO {

    @JsonProperty("id")
    private int pacientesID;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    public PacientesDTO() {
    }

    public PacientesDTO(int pacientesID, String nombre, String apellido, String telefono, String email) {
        this.pacientesID = pacientesID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }
    
    public int getPacientesID() {
        return pacientesID;
    }
    public void setPacientesID(int pacientesID) {
        this.pacientesID = pacientesID;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
