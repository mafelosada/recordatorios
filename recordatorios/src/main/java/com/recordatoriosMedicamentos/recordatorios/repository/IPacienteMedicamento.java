package com.recordatoriosMedicamentos.recordatorios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordatoriosMedicamentos.recordatorios.model.Paciente_Medicamento;

public interface IPacienteMedicamento extends JpaRepository<Paciente_Medicamento, Integer> {  
}
