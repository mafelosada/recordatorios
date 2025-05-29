package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Paciente_Medicamento;

public interface Ipacientes_medicamentos extends JpaRepository<Paciente_Medicamento, Integer> {
    @Query("SELECT pm FROM pacientes_medicamentosDTO pm WHERE pm.paciente.nombre LIKE %?1%")
    List<Paciente_Medicamento> findByPacienteNameContainingIgnoreCase(String nombre);

    @Query("SELECT pm FROM pacientes_medicamentosDTO pm WHERE pm.medicamento.nombreMedicamentos LIKE %?1%")
    List<Paciente_Medicamento> findByMedicamentoNameContainingIgnoreCase(String nombre);

}
