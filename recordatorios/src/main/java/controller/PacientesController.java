package controller;

import DTO.PacientesDTO;
import DTO.ResponsesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.pacienteService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/pacientes/")
@CrossOrigin(origins = "http://")
public class PacientesController {
    @Autowired
    private pacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPaciente(@RequestBody PacientesDTO paciente) {
        ResponsesDTO respuesta = pacienteService.save(paciente);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllPacientes() {
        var listaPacientes = pacienteService.findAll();
        return new ResponseEntity<>(listaPacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable int id) {
        var paciente = pacienteService.findById(id);
        if (!paciente.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListPacientesForName(@PathVariable String filter) {
        var pacientes = pacienteService.getListPacientesForName(filter);
        if (pacientes.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable int id) {
        var message = pacienteService.deletePaciente(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
