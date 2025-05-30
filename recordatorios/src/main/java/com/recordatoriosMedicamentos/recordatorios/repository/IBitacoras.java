package com.recordatoriosMedicamentos.recordatorios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordatoriosMedicamentos.recordatorios.model.Bitacoras;

public interface IBitacoras extends JpaRepository<Bitacoras, Integer> {
}
