package com.recordatoriosMedicamentos.recordatorios.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordatoriosMedicamentos.recordatorios.DTO.PacienteMedicamentoDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Paciente_Medicamento;
import com.recordatoriosMedicamentos.recordatorios.service.PacienteMedicamentoService;

@RestController
@RequestMapping("/pacientesMedicamentos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacienteMedicamentoController {

    @Autowired
    private PacienteMedicamentoService pacienteMedicamentoService;

    @GetMapping("/")
    public ResponseEntity<List<PacienteMedicamentoDTO>> getAll() {
        List<Paciente_Medicamento> lista = pacienteMedicamentoService.findAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PacienteMedicamentoDTO> dtoList = lista.stream()
            .map(pacienteMedicamentoService::convertToDTO)
            .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable int id) {
        Optional<Paciente_Medicamento> pacienteMedicamentoOpt = pacienteMedicamentoService.findById(id);
        if (pacienteMedicamentoOpt.isEmpty()) {
            return new ResponseEntity<>(new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Registro no encontrado"), HttpStatus.NOT_FOUND);
        }
        PacienteMedicamentoDTO dto = pacienteMedicamentoService.convertToDTO(pacienteMedicamentoOpt.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponsesDTO> create(@RequestBody PacienteMedicamentoDTO dto) {
        ResponsesDTO response = pacienteMedicamentoService.save(dto);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
