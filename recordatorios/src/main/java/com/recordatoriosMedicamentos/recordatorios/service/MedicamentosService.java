package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.MedicamentoDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.repository.Imedicamentos;

@Service
public class MedicamentosService {

    @Autowired
    private Imedicamentos data;

    public List<Medicamento> findAll() {
        return data.findAll();
    }

    public List<Medicamento> getListMedicamentosForName(String nombre) {
        return data.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Medicamento> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO save(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = convertToModel(medicamentoDTO);
        data.save(medicamento);
        return new ResponsesDTO(HttpStatus.OK.toString(), "Se guardó correctamente");
    }

    // Convertir DTO a modelo
    private Medicamento convertToModel(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento();
        medicamento.setMedicamentosID(medicamentoDTO.getMedicamentosID());
        medicamento.setNombreMedicamentos(medicamentoDTO.getNombreMedicamentos());
        return medicamento;
    }

    // Convertir modelo a DTO
    public MedicamentoDTO convertToDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
            medicamento.getMedicamentosID(),
            medicamento.getNombreMedicamentos()
        );
        return medicamentoDTO;
    }

}
