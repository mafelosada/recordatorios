package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "pacientes")
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pacientesID")
    private int pacientesID;

    @Column(name="nombre", length=75, nullable=false)
    private String nombre;

    @Column(name="apellido", length=75, nullable=false)
    private String apellido;

    @Column(name="telefono", length=15, nullable=false)
    private String telefono;

    @Column(name="email", length=100, nullable=false)
    private String email;

    public Pacientes() {
    }

    public Pacientes(int pacientesID, String nombre, String apellido, String telefono, String email) {
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
