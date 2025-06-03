package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.PacienteMedicamentoDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Medicamento;
import com.recordatoriosMedicamentos.recordatorios.model.Paciente_Medicamento;
import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;
import com.recordatoriosMedicamentos.recordatorios.repository.IPacienteMedicamento;
import com.recordatoriosMedicamentos.recordatorios.repository.Imedicamentos;
import com.recordatoriosMedicamentos.recordatorios.repository.Ipacientes;

@Service
public class PacienteMedicamentoService {

    @Autowired
    private IPacienteMedicamento pacienteMedicamentoRepo;

    @Autowired
    private Ipacientes pacientesRepo;

    @Autowired
    private Imedicamentos medicamentosRepo;

    @Autowired
    private emailService emailService;

    public List<Paciente_Medicamento> findAll() {
        return pacienteMedicamentoRepo.findAll();
    }

    public Optional<Paciente_Medicamento> findById(int id) {
        return pacienteMedicamentoRepo.findById(id);
    }

    public ResponsesDTO save(PacienteMedicamentoDTO dto) {
        // Validar existencia de paciente y medicamento
        Optional<Pacientes> pacienteOpt = pacientesRepo.findById(dto.getPacienteId());
        if (pacienteOpt.isEmpty()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Paciente no encontrado");
        }

        Optional<Medicamento> medicamentoOpt = medicamentosRepo.findById(dto.getMedicamentoId());
        if (medicamentoOpt.isEmpty()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Medicamento no encontrado");
        }

        Paciente_Medicamento pacienteMedicamento = new Paciente_Medicamento();
        pacienteMedicamento.setPaciente(pacienteOpt.get());
        pacienteMedicamento.setMedicamento(medicamentoOpt.get());
        pacienteMedicamento.setDosis(dto.getDosis());
        pacienteMedicamento.setHorario(dto.getHorario());

        pacienteMedicamentoRepo.save(pacienteMedicamento);

        // Enviar correo de confirmación
        Pacientes paciente = pacienteOpt.get();
        Medicamento medicamento = medicamentoOpt.get();

        String email = paciente.getEmail(); // asegúrate que exista este campo
        String nombrePaciente = paciente.getNombre(); // asegúrate que exista este campo
        String nombreMedicamento = medicamento.getNombreMedicamentos(); // asegúrate que exista este campo

        if (email != null && !email.isEmpty()) {
            emailService.enviarConfirmacionRegistroMedicamento(
                email,
                nombrePaciente,
                nombreMedicamento,
                dto.getDosis(),
                dto.getHorario().toString() 
            );
        }

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se guardó correctamente y se envió el correo");
    } // <- Esta llave de cierre faltaba

    public PacienteMedicamentoDTO convertToDTO(Paciente_Medicamento pm) {
        return new PacienteMedicamentoDTO(
            pm.getPacientes_medicamentosID(),
            pm.getPaciente().getPacientesID(),
            pm.getMedicamento().getMedicamentosID(),
            pm.getDosis(),
            pm.getHorario() // <- Cambié esto: quité el .toString() porque el constructor espera Time, no String
        );
    }
}