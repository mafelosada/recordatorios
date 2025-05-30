package com.recordatoriosMedicamentos.recordatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordatoriosMedicamentos.recordatorios.DTO.BitacorasDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.service.bitacoraService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/bitacora/")
@CrossOrigin(origins = "http://")
public class bitacoraController {
    @Autowired
    private bitacoraService bitacoraService;

    @PostMapping("/")
    public ResponseEntity<Object> registerBitacora(@RequestBody BitacorasDTO bitacora) {
        ResponsesDTO respuesta = bitacoraService.save(bitacora);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllBitacoras() {
        var listaBitacoras = bitacoraService.findAll();
        return new ResponseEntity<>(listaBitacoras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBitacora(@PathVariable int id) {
        var bitacora = bitacoraService.findById(id);
        if (!bitacora.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bitacora, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListBitacorasForName(@PathVariable String filter) {
        var bitacoras = bitacoraService.getListBitacorasForPacienteName(filter);
        if (bitacoras.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bitacoras, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBitacora(@PathVariable int id) {
        var message = bitacoraService.deleteBitacora(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBitacora(@PathVariable int id, @RequestBody BitacorasDTO bitacoraDTO) {
        var message = bitacoraService.updateBitacora(id, bitacoraDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
