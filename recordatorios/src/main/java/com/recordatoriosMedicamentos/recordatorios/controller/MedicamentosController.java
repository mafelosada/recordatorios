package com.recordatoriosMedicamentos.recordatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordatoriosMedicamentos.recordatorios.DTO.MedicamentoDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.service.MedicamentosService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController {

    @Autowired
    private MedicamentosService medicamentoService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMedicamento(@RequestBody MedicamentoDTO medicamento) {
        ResponsesDTO respuesta = medicamentoService.save(medicamento);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable int id) {
        var medicamento = medicamentoService.findById(id);
        if (medicamento.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(medicamento.get(), HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListMedicamentosForName(@PathVariable String filter) {
        var medicamentos = medicamentoService.getListMedicamentosForName(filter);
        if (medicamentos.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
}
