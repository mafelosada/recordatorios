package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import repository.Ipacientes_medicamentos;
import repository.Ipacientes;
import repository.Imedicamentos;
import model.Paciente_Medicamento;
import DTO.Paciente_medicamentoDTO;
import DTO.ResponsesDTO;

@Service
public class paciente_medicamentoService {
    @Autowired
    private Ipacientes_medicamentos data;

    @Autowired
    private Ipacientes pacienteRepository;

    @Autowired
    private Imedicamentos medicamentoRepository;

    public List<Paciente_Medicamento> findAll() {
        return data.findAll();
    }

    public List<Paciente_Medicamento> getListPacienteMedicamentosForPacienteName(String nombre) {
        return data.findByPacienteNameContainingIgnoreCase(nombre);
    }

    public List<Paciente_Medicamento> getListPacienteMedicamentosForMedicamentoName(String nombre) {
        return data.findByMedicamentoNameContainingIgnoreCase(nombre);
    }
    public Optional<Paciente_Medicamento> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO deletePacienteMedicamento(int id) {
        Optional<Paciente_Medicamento> pacienteMedicamento = findById(id);
        if (!pacienteMedicamento.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        data.deleteById(id);  // Aquí realmente se elimina el registro.

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se eliminó correctamente");
    }

    public ResponsesDTO save(Paciente_medicamentoDTO pacienteMedicamentoDTO) {
        Paciente_Medicamento pacienteMedicamento = convertToModel(pacienteMedicamentoDTO);
        data.save(pacienteMedicamento);
        ResponsesDTO respuesta = new ResponsesDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public ResponsesDTO updatePacienteMedicamento(int id, Paciente_medicamentoDTO pacienteMedicamentoDTO) {
        Optional<Paciente_Medicamento> optional = findById(id);
        if (!optional.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        Paciente_Medicamento paciente_Medicamento = convertToModel(pacienteMedicamentoDTO);
        paciente_Medicamento.setPacientes_medicamentosID(id); 
        
        data.save(paciente_Medicamento);

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se actualizó correctamente");
    }

    public Paciente_medicamentoDTO convertToDTO(Paciente_Medicamento pacienteMedicamento) {
        Paciente_medicamentoDTO pacienteMedicamentoDTO = new Paciente_medicamentoDTO(
            pacienteMedicamento.getPacientes_medicamentosID(),
            pacienteMedicamento.getPaciente(),
            pacienteMedicamento.getMedicamento(),
            pacienteMedicamento.getDosis(),
            pacienteMedicamento.getHorario()
        );
        return pacienteMedicamentoDTO;
    }

    public Paciente_Medicamento convertToModel(Paciente_medicamentoDTO pacienteMedicamentoDTO) {
        var paciente = pacienteRepository.findById(pacienteMedicamentoDTO.getPaciente().getPacientesID())
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        var medicamento = medicamentoRepository.findById(pacienteMedicamentoDTO.getMedicamento().getMedicamentosID())
            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

       Paciente_Medicamento pacienteMedicamento = new Paciente_Medicamento();
            pacienteMedicamento.setPaciente(paciente);
            pacienteMedicamento.setMedicamento(medicamento);
            pacienteMedicamento.setDosis(pacienteMedicamentoDTO.getDosis());
            pacienteMedicamento.setHorario(pacienteMedicamentoDTO.getHorario());
        return pacienteMedicamento;
    }

}
