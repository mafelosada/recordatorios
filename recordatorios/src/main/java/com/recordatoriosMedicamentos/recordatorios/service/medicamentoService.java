package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.MedicamentoDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.repository.Imedicamentos;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class medicamentoService {
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

    public ResponsesDTO deleteMedicamento(int id) {
        Optional<Medicamento> medicamento = findById(id);
        if (!medicamento.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        data.deleteById(id);  // Aquí realmente se elimina el registro.

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se eliminó correctamente");
    }

    public ResponsesDTO save(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = convertToModel(medicamentoDTO);
        data.save(medicamento);
        ResponsesDTO respuesta = new ResponsesDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }
    public ResponsesDTO updateMedicamento(int id, MedicamentoDTO medicamentoDTO) {
        Optional<Medicamento> medicamento = findById(id);
        if (!medicamento.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        Medicamento updatedMedicamento = medicamento.get();
        updatedMedicamento.setNombreMedicamentos(medicamentoDTO.getNombreMedicamentos());
        data.save(updatedMedicamento);

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se actualizó correctamente");
    }

    private Medicamento convertToModel(MedicamentoDTO medicamentoDTO) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNombreMedicamentos(medicamentoDTO.getNombreMedicamentos());
        return medicamento;
    }

    public MedicamentoDTO convertToDTO(Medicamento medicamento) {
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO(
            0,
            medicamento.getNombreMedicamentos()
        );
        return medicamentoDTO;
    }




}
