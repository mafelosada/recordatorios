document.addEventListener("DOMContentLoaded", () => {
  // Mostrar un formulario y ocultar los demás
  window.mostrarFormulario = (tipo) => {
    document.getElementById("formPaciente").style.display = "none"
    document.getElementById("formMedicamento").style.display = "none"
    document.getElementById("formPacienteMedicamento").style.display = "none"

    if (tipo === "paciente") {
      document.getElementById("formPaciente").style.display = "block"
    } else if (tipo === "medicamento") {
      document.getElementById("formMedicamento").style.display = "block"
    } else if (tipo === "pacienteMedicamento") {
      document.getElementById("formPacienteMedicamento").style.display = "block"
      // Cargar los selects y las asignaciones existentes
      loadPacientes()
      loadMedicamentos()
      loadAsignaciones()
    }
  }

  // Función para convertir tiempo HTML5 a formato compatible con java.sql.Time
  function formatTimeForBackend(timeString) {
    // timeString viene en formato "HH:MM" del input type="time"
    // Necesitamos convertirlo a "HH:MM:SS" para java.sql.Time
    if (timeString && timeString.includes(":")) {
      return timeString + ":00" // Agregar segundos
    }
    return timeString
  }

  // Manejo del formulario de Paciente
  document.getElementById("pacienteForm").addEventListener("submit", function (e) {
    e.preventDefault()

    const datos = Object.fromEntries(new FormData(this))
    fetch("http://localhost:8080/pacientes/", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(datos),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error ${response.status}: ${response.statusText}`)
        }
        return response.json()
      })
      .then((data) => {
        alert("Paciente registrado con éxito")
        this.reset() // Limpiar formulario
      })
      .catch((error) => {
        console.error("Error al registrar paciente:", error)
        alert("Error al registrar paciente: " + error.message)
      })
  })

  // Manejo del formulario de Medicamento
  document.getElementById("medicamentoForm").addEventListener("submit", function (e) {
    e.preventDefault()

    const datos = Object.fromEntries(new FormData(this))
    fetch("http://localhost:8080/medicamentos/", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(datos),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error ${response.status}: ${response.statusText}`)
        }
        return response.json()
      })
      .then((data) => {
        alert("Medicamento registrado con éxito")
        this.reset() // Limpiar formulario
      })
      .catch((error) => {
        console.error("Error al registrar medicamento:", error)
        alert("Error al registrar medicamento: " + error.message)
      })
  })

  // Manejo del formulario de PacienteMedicamento - CORREGIDO
  document.getElementById("pacienteMedicamentoForm").addEventListener("submit", function (e) {
    e.preventDefault()

    const formData = new FormData(this)

    // Formatear el horario correctamente
    const horarioRaw = formData.get("horario")
    const horarioFormatted = formatTimeForBackend(horarioRaw)

    const datos = {
      pacienteId: Number.parseInt(formData.get("pacienteId")),
      medicamentoId: Number.parseInt(formData.get("medicamentoId")),
      dosis: formData.get("dosis"),
      horario: horarioFormatted, // Usar el horario formateado
    }

    console.log("Datos originales del formulario:", Object.fromEntries(formData))
    console.log("Datos formateados para enviar:", datos)

    fetch("http://localhost:8080/pacientesMedicamentos/", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(datos),
    })
      .then((response) => {
        if (!response.ok) {
          return response.json().then((errorData) => {
            throw new Error(`Error ${response.status}: ${errorData.message || response.statusText}`)
          })
        }
        return response.json()
      })
      .then((data) => {
        alert("Asignación realizada con éxito")
        this.reset() // Limpiar formulario
        loadAsignaciones() // Recargar la tabla de asignaciones
      })
      .catch((error) => {
        console.error("Error al asignar medicamento:", error)
        alert("Error al asignar medicamento: " + error.message)
      })
  })

  // Función para cargar pacientes en el select
  async function loadPacientes() {
    try {
      const response = await fetch("http://localhost:8080/pacientes/", {
        method: "GET",
        headers: { Accept: "application/json" },
      })

      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${response.statusText}`)
      }

      const pacientesList = await response.json()
      const selectPacientes = document.getElementById("pacientesSelect")
      selectPacientes.innerHTML = '<option value="">Seleccione un paciente</option>'

      pacientesList.forEach((paciente) => {
        const option = document.createElement("option")
        // Usar el ID correcto según tu modelo
        option.value = paciente.pacientesID
        option.textContent = `${paciente.nombre} ${paciente.apellido}`
        selectPacientes.appendChild(option)
      })

      console.log("Pacientes cargados:", pacientesList.length)
    } catch (error) {
      console.error("Error al cargar pacientes:", error)
      alert("No se pudieron cargar los pacientes: " + error.message)
    }
  }

  // Función para cargar medicamentos en el select
  async function loadMedicamentos() {
    try {
      const response = await fetch("http://localhost:8080/medicamentos/", {
        method: "GET",
        headers: { Accept: "application/json" },
      })

      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${response.statusText}`)
      }

      const medicamentosList = await response.json()
      const selectMedicamentos = document.getElementById("medicamentosSelect")
      selectMedicamentos.innerHTML = '<option value="">Seleccione un medicamento</option>'

      medicamentosList.forEach((medicamento) => {
        const option = document.createElement("option")
        // Usar el ID correcto según tu modelo
        option.value = medicamento.medicamentosID
        option.textContent = medicamento.nombreMedicamentos
        selectMedicamentos.appendChild(option)
      })

      console.log("Medicamentos cargados:", medicamentosList.length)
    } catch (error) {
      console.error("Error al cargar medicamentos:", error)
      alert("No se pudieron cargar los medicamentos: " + error.message)
    }
  }

  // Función para cargar las asignaciones existentes
  async function loadAsignaciones() {
    try {
      const response = await fetch("http://localhost:8080/pacientesMedicamentos/", {
        method: "GET",
        headers: { Accept: "application/json" },
      })

      if (!response.ok) {
        if (response.status === 204) {
          // No content es normal si no hay asignaciones
          document.getElementById("asignacionesTable").querySelector("tbody").innerHTML =
            '<tr><td colspan="4" style="text-align: center;">No hay asignaciones registradas</td></tr>'
          return
        }
        throw new Error(`Error ${response.status}: ${response.statusText}`)
      }

      const asignacionesList = await response.json()
      const tableBody = document.getElementById("asignacionesTable").querySelector("tbody")
      tableBody.innerHTML = ""

      // Necesitamos cargar los nombres de pacientes y medicamentos
      const pacientesResponse = await fetch("http://localhost:8080/pacientes/")
      const medicamentosResponse = await fetch("http://localhost:8080/medicamentos/")

      if (!pacientesResponse.ok || !medicamentosResponse.ok) {
        throw new Error("Error al cargar datos relacionados")
      }

      const pacientes = await pacientesResponse.json()
      const medicamentos = await medicamentosResponse.json()

      // Crear mapas para búsqueda rápida
      const pacientesMap = new Map(pacientes.map((p) => [p.pacientesID, `${p.nombre} ${p.apellido}`]))
      const medicamentosMap = new Map(medicamentos.map((m) => [m.medicamentosID, m.nombreMedicamentos]))

      asignacionesList.forEach((asignacion) => {
        const row = document.createElement("tr")

        // Obtener nombres usando los mapas
        const nombrePaciente = pacientesMap.get(asignacion.pacienteId) || "Desconocido"
        const nombreMedicamento = medicamentosMap.get(asignacion.medicamentoId) || "Desconocido"

        // Formatear el horario para mostrar
        const horarioDisplay = asignacion.horario || "No especificado"

        row.innerHTML = `
                    <td>${nombrePaciente}</td>
                    <td>${nombreMedicamento}</td>
                    <td>${asignacion.dosis}</td>
                    <td>${horarioDisplay}</td>
                `
        tableBody.appendChild(row)
      })

      console.log("Asignaciones cargadas:", asignacionesList.length)
    } catch (error) {
      console.error("Error al cargar asignaciones:", error)
      document
        .getElementById("asignacionesTable")
        .querySelector(
          "tbody",
        ).innerHTML = `<tr><td colspan="4" style="text-align: center; color: red;">Error al cargar asignaciones: ${error.message}</td></tr>`
    }
  }
})
