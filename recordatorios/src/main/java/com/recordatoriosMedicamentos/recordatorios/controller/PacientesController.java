package com.recordatoriosMedicamentos.recordatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordatoriosMedicamentos.recordatorios.DTO.PacientesDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.service.PacientesService;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacientesController {
    @Autowired
    private PacientesService pacienteService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllPacientes() {
        var pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return new ResponseEntity<>(new ResponsesDTO(HttpStatus.NO_CONTENT.toString(), "No hay pacientes registrados"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> registerPaciente(@RequestBody PacientesDTO paciente) {
        ResponsesDTO respuesta = pacienteService.save(paciente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
}
