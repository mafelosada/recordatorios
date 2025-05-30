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
public class PacientesService {

    @Autowired
    private Ipacientes data;

    public List<Pacientes> findAll() {
        return data.findAll();
    }

    public Optional<Pacientes> findById(int id) {
        return data.findById(id);
    }

    // Búsqueda por nombre - usar solo después de corregir el repository
    public List<Pacientes> getListPacientesForName(String nombre) {
        return data.findByNombreContainingIgnoreCase(nombre);
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
            0, // El ID se generará automáticamente
            pacientesDTO.getNombre(),
            pacientesDTO.getApellido(),
            pacientesDTO.getTelefono(),
            pacientesDTO.getEmail()
        );
        return paciente;
    }
}