package com.recordatoriosMedicamentos.recordatorios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recordatoriosMedicamentos.recordatorios.DTO.BitacorasDTO;
import com.recordatoriosMedicamentos.recordatorios.DTO.ResponsesDTO;
import com.recordatoriosMedicamentos.recordatorios.model.Bitacoras;
import com.recordatoriosMedicamentos.recordatorios.repository.Ibitacora;
import com.recordatoriosMedicamentos.recordatorios.repository.Imedicamentos;
import com.recordatoriosMedicamentos.recordatorios.repository.Ipacientes;


@Service
public class bitacoraService {
    @Autowired
    private Ibitacora data;

    @Autowired
    private Ipacientes pacienteRepository;

    @Autowired
    private Imedicamentos medicamentoRepository;

    public List<Bitacoras> findAll() {
        return data.findAll();
    }

    public List<Bitacoras> getListBitacorasForPacienteName(String nombre) {
        return data.findByPacienteNameContainingIgnoreCase(nombre);
    }

    public List<Bitacoras> getListBitacorasForMedicamentoName(String nombre) {
        return data.findByMedicamentoNameContainingIgnoreCase(nombre);
    }

    public Optional<Bitacoras> findById(int id) {
        return data.findById(id);
    }

    public ResponsesDTO deleteBitacora(int id) {
        if (!data.existsById(id)) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        data.deleteById(id);  // Aquí realmente se elimina el registro.

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se eliminó correctamente");
    }

    public ResponsesDTO save(BitacorasDTO bitacoraDTO) {
        Bitacoras bitacora = convertToModel(bitacoraDTO);
        data.save(bitacora);
        ResponsesDTO response = new ResponsesDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return response;
       
    }

    public ResponsesDTO updateBitacora(int id, BitacorasDTO bitacoraDTO) {
        Optional<Bitacoras> optional = findById(id);
        if (!optional.isPresent()) {
            return new ResponsesDTO(HttpStatus.NOT_FOUND.toString(), "El registro no existe");
        }

        Bitacoras bitacoras = convertToModel(bitacoraDTO);
        bitacoras.setBitacoraID(id);

        data.save(bitacoras);

        return new ResponsesDTO(HttpStatus.OK.toString(), "Se actualizó correctamente");

    }

    public BitacorasDTO convertToDTO(Bitacoras bitacora) {
            BitacorasDTO bitacoraDTO = new BitacorasDTO(
            bitacora.getBitacoraID(),
            bitacora.getPaciente(), 
            bitacora.getMedicamento(),
            bitacora.getFecha()
        );
        return bitacoraDTO;
    }

    public Bitacoras convertToModel(BitacorasDTO bitacoraDTO) {
        var paciente = pacienteRepository.findById(bitacoraDTO.getPaciente().getPacientesID())
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        var medicamento = medicamentoRepository.findById(bitacoraDTO.getMedicamento().getMedicamentosID())
            .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        Bitacoras bitacora = new Bitacoras();
            bitacora.setBitacoraID(bitacoraDTO.getBitacoraID());
            bitacora.setPaciente(paciente);
            bitacora.setMedicamento(medicamento);
            bitacora.setFecha(bitacoraDTO.getFecha());
        return bitacora;
    }
    

}
