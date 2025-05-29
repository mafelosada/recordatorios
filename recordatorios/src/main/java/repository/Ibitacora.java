package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Bitacoras;

public interface Ibitacora extends JpaRepository<Bitacoras, Integer> {

   @Query("SELECT pm FROM pacientes_medicamentosDTO pm WHERE pm.paciente.nombre LIKE %?1%")
    List<Bitacoras> findByPacienteNameContainingIgnoreCase(String nombre);

    @Query("SELECT pm FROM pacientes_medicamentosDTO pm WHERE pm.medicamento.nombreMedicamentos LIKE %?1%")
    List<Bitacoras> findByMedicamentoNameContainingIgnoreCase(String nombre);

}
