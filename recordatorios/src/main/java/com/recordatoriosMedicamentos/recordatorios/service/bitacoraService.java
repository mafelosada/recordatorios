package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.BitacoraDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Bitacoras;
import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;
import com.recordatoriosMedicamentos.recordatorios.repository.IBitacoras;
import com.recordatoriosMedicamentos.recordatorios.repository.Imedicamentos;
import com.recordatoriosMedicamentos.recordatorios.repository.Ipacientes;

@Service
public class bitacoraService {

    @Autowired
    private IBitacoras bitacoraRepository;

    @Autowired
    private Ipacientes pacientesRepository;

    @Autowired
    private Imedicamentos medicamentosRepository;

    public List<Bitacoras> findAll() {
        return bitacoraRepository.findAll();
    }

    public Optional<Bitacoras> findById(int id) {
        return bitacoraRepository.findById(id);
    }

    public ResponsesDTO save(BitacoraDTO dto) {
        Optional<Pacientes> pacienteOpt = pacientesRepository.findById(dto.getPacienteID());
        if (pacienteOpt.isEmpty()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Paciente no encontrado");
        }

        Optional<Medicamento> medicamentoOpt = medicamentosRepository.findById(dto.getMedicamentoID());
        if (medicamentoOpt.isEmpty()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Medicamento no encontrado");
        }

        Bitacoras bitacora = new Bitacoras();
        bitacora.setPaciente(pacienteOpt.get());
        bitacora.setMedicamento(medicamentoOpt.get());
        bitacora.setFecha(dto.getFecha());

        bitacoraRepository.save(bitacora);

        return new ResponsesDTO(HttpStatus.OK.toString(), "Registro guardado correctamente");
    }

    public BitacoraDTO convertToDTO(Bitacoras bitacora) {
        return new BitacoraDTO(
            bitacora.getBitacoraID(),
            bitacora.getPaciente().getPacientesID(),
            bitacora.getMedicamento().getMedicamentosID(),
            bitacora.getFecha()
        );
    }
    
}
