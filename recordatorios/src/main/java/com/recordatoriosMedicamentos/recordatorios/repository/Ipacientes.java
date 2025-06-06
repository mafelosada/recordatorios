package com.recordatoriosMedicamentos.recordatorios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;

public interface Ipacientes extends JpaRepository<Pacientes, Integer> {

    // Si no tienes campo status, usa findAll() o elimina este método
    // List<Pacientes> findAllActivePacientes();

    @Query("SELECT p FROM Pacientes p WHERE p.nombre LIKE %?1%")
    List<Pacientes> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Pacientes p WHERE p.apellido LIKE %?1%")
    List<Pacientes> findByApellidoContaining(String apellido);

    @Query("SELECT p FROM Pacientes p WHERE p.email LIKE %?1%")
    List<Pacientes> findByEmailContainingIgnoreCase(String email);
}
