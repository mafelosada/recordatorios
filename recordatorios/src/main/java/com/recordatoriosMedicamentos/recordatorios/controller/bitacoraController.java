package com.recordatoriosMedicamentos.recordatorios.controller;

import java.util.List;
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

import com.recordatoriosMedicamentos.recordatorios.DTO.BitacoraDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Bitacoras;
import com.recordatoriosMedicamentos.recordatorios.service.bitacoraService;

@RestController
@RequestMapping("/bitacoras")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class bitacoraController {

    @Autowired
    private bitacoraService bitacoraService;

    @GetMapping("/")
    public ResponseEntity<List<BitacoraDTO>> getAll() {
        List<Bitacoras> list = bitacoraService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BitacoraDTO> dtoList = list.stream()
            .map(bitacoraService::convertToDTO)
            .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable int id) {
        var bitacoraOpt = bitacoraService.findById(id);
        if (bitacoraOpt.isEmpty()) {
            return new ResponseEntity<>(new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "Registro no encontrado"), HttpStatus.NOT_FOUND);
        }
        BitacoraDTO dto = bitacoraService.convertToDTO(bitacoraOpt.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ResponsesDTO> create(@RequestBody BitacoraDTO dto) {
        ResponsesDTO response = bitacoraService.save(dto);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
