package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import service.paciente_medicamentoService;
import DTO.Paciente_medicamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/pacientes_medicamentos/")
@CrossOrigin(origins = "http://")
public class pacientes_MedicamentosController {
    @Autowired
    private paciente_medicamentoService pacientesMedicamentosService;

    @PostMapping("/")
    public ResponseEntity<Object> registerPacienteMedicamento(@RequestBody Paciente_medicamentoDTO pacienteMedicamento) {
        var message = pacientesMedicamentosService.save(pacienteMedicamento);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getAllPacientesMedicamentos() {
        var listaPacientesMedicamentos = pacientesMedicamentosService.findAll();
        return new ResponseEntity<>(listaPacientesMedicamentos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePacienteMedicamento(@PathVariable int id) {
        var pacienteMedicamento = pacientesMedicamentosService.findById(id);
        if (!pacienteMedicamento.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pacienteMedicamento, HttpStatus.OK);
    }
    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListPacientesMedicamentosForName(@PathVariable String filter) {
        var pacientesMedicamentos = pacientesMedicamentosService.getListPacienteMedicamentosForPacienteName(filter);
        if (pacientesMedicamentos.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pacientesMedicamentos, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePacienteMedicamento(@PathVariable int id) {
        var message = pacientesMedicamentosService.deletePacienteMedicamento(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePacienteMedicamento(@PathVariable int id, @RequestBody Paciente_medicamentoDTO pacienteMedicamentoDTO) {
        var message = pacientesMedicamentosService.updatePacienteMedicamento(id, pacienteMedicamentoDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}
