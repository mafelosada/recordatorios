package com.recordatoriosMedicamentos.recordatorios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordatoriosMedicamentos.recordatorios.model.Bitacoras;

public interface Ibitacora extends JpaRepository<Bitacoras, Integer> {

    List<Bitacoras> findByPacienteNameContainingIgnoreCase(String nombre);

    List<Bitacoras> findByMedicamentoNameContainingIgnoreCase(String nombre);

}
