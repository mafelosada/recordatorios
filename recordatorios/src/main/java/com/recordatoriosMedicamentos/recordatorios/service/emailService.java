package com.recordatoriosMedicamentos.recordatorios.service;

// Imports correctos para Spring Boot
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Import correcto para MessagingException
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Método para enviar email de nueva cuenta
    public void sendNewAccountEmail(String addressMail, String username) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(addressMail);
            helper.setSubject("¡Bienvenido!");
            
            String htmlContent = "<h2>Hola " + username + "</h2>" +
                               "<p>Nos alegra que te hayas registrado.</p>" +
                               "<p>Ahora puedes acceder y disfrutar de todas las funcionalidades que tenemos para ti.</p>" +
                               "<p><strong>¡Esperamos que disfrutes la experiencia!</strong></p>";
            
            helper.setText(htmlContent, true); // true = es HTML
            
            javaMailSender.send(message);
            
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo de nueva cuenta: " + e.getMessage());
        }
    }
    
    // Método para recordatorios de medicamentos
    public void enviarRecordatorioMedicamento(String email, String nombrePaciente, 
                                             String medicamento, String dosis) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(email);
            helper.setSubject("Recordatorio de Medicamento: " + medicamento);
            
            String htmlContent = "<h2>Hola " + nombrePaciente + "</h2>" +
                               "<p>Es hora de tomar tu medicamento:</p>" +
                               "<p><strong>Medicamento:</strong> " + medicamento + "</p>" +
                               "<p><strong>Dosis:</strong> " + dosis + "</p>" +
                               "<p>¡No olvides cuidar tu salud!</p>";
            
            helper.setText(htmlContent, true);
            
            javaMailSender.send(message);
            
        } catch (MessagingException e) {
            System.out.println("Error al enviar recordatorio: " + e.getMessage());
        }
    }

    // Método para confirmar registro de nuevo medicamento
    public void enviarConfirmacionRegistroMedicamento(String email, String nombrePaciente, 
                                                    String medicamento, String dosis, String horario) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(email);
            helper.setSubject("Confirmación: Nuevo Medicamento Registrado");
            
            String htmlContent = "<h2>Hola " + nombrePaciente + "</h2>" +
                               "<p>Te confirmamos que se ha registrado exitosamente un nuevo medicamento en tu plan de tratamiento:</p>" +
                               "<p><strong>📋 Medicamento:</strong> " + medicamento + "</p>" +
                               "<p><strong>💊 Dosis:</strong> " + dosis + "</p>" +
                               "<p><strong>⏰ Horario:</strong> " + horario + "</p>" +
                               "<p>A partir de ahora recibirás recordatorios automáticos en el horario establecido.</p>" +
                               "<p><strong>¡Recuerda seguir tu tratamiento según las indicaciones médicas!</strong></p>";
            
            helper.setText(htmlContent, true);
            
            javaMailSender.send(message);
            
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo de confirmación de medicamento: " + e.getMessage());
        }
    }
}
