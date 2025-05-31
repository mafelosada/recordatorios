
        function mostrarFormulario(tipo) {
            document.getElementById("formPaciente").style.display = (tipo === 'paciente') ? "block" : "none";
            document.getElementById("formMedicamento").style.display = (tipo === 'medicamento') ? "block" : "none";
        }

        // Enviar datos del formulario de paciente
        document.getElementById("pacienteForm").addEventListener("submit", function(event) {
            event.preventDefault();
            const datos = Object.fromEntries(new FormData(this));
            fetch("http://localhost:8080/pacientes/", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(datos)
            }).then(resp => resp.json()).then(data => alert("Paciente registrado")).catch(err => alert("Error"));
        });

        // Enviar datos del formulario de medicamento
        document.getElementById("medicamentoForm").addEventListener("submit", function(event) {
            event.preventDefault();
            const datos = Object.fromEntries(new FormData(this));
            fetch("http://localhost:8080/medicamentos/", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(datos)
            }).then(resp => resp.json()).then(data => alert("Medicamento registrado")).catch(err => alert("Error"));
        });