package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import DTO.MedicamentoDTO;
import DTO.ResponsesDTO;

import service.medicamentoService;

@RestController
@RequestMapping("/medicamentos/")
@CrossOrigin(origins = "http://")
public class medicamentoController {

    @Autowired
    private medicamentoService medicamentoService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMedicamento(@RequestBody MedicamentoDTO medicamento) {
        ResponsesDTO respuesta = medicamentoService.save(medicamento);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMedicamentos() {
        var listaMedicamentos = medicamentoService.findAll();
        return new ResponseEntity<>(listaMedicamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable int id) {
        var medicamento = medicamentoService.findById(id);
        if (!medicamento.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(medicamento, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListMedicamentosForName(@PathVariable String filter) {
        var medicamentos = medicamentoService.getListMedicamentosForName(filter);
        if (medicamentos.isEmpty())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable int id) {
        var message = medicamentoService.deleteMedicamento(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable int id, @RequestBody MedicamentoDTO medicamentoDTO) {
        var message = medicamentoService.updateMedicamento(id, medicamentoDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
