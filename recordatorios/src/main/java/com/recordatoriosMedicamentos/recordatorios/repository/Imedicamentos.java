package com.recordatoriosMedicamentos.recordatorios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;

public interface Imedicamentos extends JpaRepository<Medicamento, Integer> {

   @Query("SELECT m FROM Medicamento m WHERE LOWER(m.nombreMedicamentos) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);

}
