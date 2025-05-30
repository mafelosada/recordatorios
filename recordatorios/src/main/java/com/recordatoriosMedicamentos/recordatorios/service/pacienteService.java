package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.PacientesDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Pacientes;
import com.recordatoriosMedicamentos.recordatorios.repository.Ipacientes;

@Service
public class pacienteService {
    @Autowired
    private Ipacientes data;

    public List<Pacientes> findAll() {
        return data.findAll();
    }

    public List<Pacientes> getListPacientesForName(String nombre) {
        return data.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Pacientes> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO deletePaciente(int id) {
        Optional<Pacientes> paciente = findById(id);
        if (!paciente.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        data.deleteById(id);  // Aquí realmente se elimina el registro.

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se eliminó correctamente");
    }

    public ResponsesDTO save(PacientesDTO pacientesDTO) {
        Pacientes pacientes = convertToModel(pacientesDTO);
        data.save(pacientes);
        ResponsesDTO respuesta = new ResponsesDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public ResponsesDTO updatePaciente(int id, PacientesDTO pacientesDTO) {
        Optional<Pacientes> pacientes = findById(id);
        if (!pacientes.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        Pacientes updatedPaciente = pacientes.get();
        updatedPaciente.setNombre(pacientesDTO.getNombre());
        updatedPaciente.setApellido(pacientesDTO.getApellido());
        updatedPaciente.setTelefono(pacientesDTO.getTelefono());
        updatedPaciente.setEmail(pacientesDTO.getEmail());
        data.save(updatedPaciente);

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se actualizó correctamente");
    }

    public PacientesDTO convertToDTO(Pacientes paciente) {
        PacientesDTO pacientesDTO = new PacientesDTO(
            paciente.getPacientesID(),
            paciente.getNombre(),
            paciente.getApellido(),
            paciente.getTelefono(),
            paciente.getEmail()
        );
        return pacientesDTO;
    }

    public Pacientes convertToModel(PacientesDTO pacientesDTO) {
        Pacientes paciente = new Pacientes(
            0,
            pacientesDTO.getNombre(),
            pacientesDTO.getApellido(),
            pacientesDTO.getTelefono(),
            pacientesDTO.getEmail()
        );
        return paciente;
    }

}
