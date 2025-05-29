package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Pacientes;

public interface Ipacientes extends JpaRepository<Pacientes, Integer> {

    @Query("SELECT p FROM pacienteDTO p WHERE p.status != false")
    List<Pacientes> findAllActivePacientes();

    @Query("SELECT p FROM pacienteDTO p WHERE p.nombre LIKE %?1%")
    List<Pacientes> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM pacienteDTO p WHERE p.apellido LIKE %?1%")
    List<Pacientes> findByApellidoContaining(String apellido);

    @Query("SELECT p FROM pacienteDTO p WHERE p.email LIKE %?1%")
    List<Pacientes> findByEmailContainingIgnoreCase(String email);

}
