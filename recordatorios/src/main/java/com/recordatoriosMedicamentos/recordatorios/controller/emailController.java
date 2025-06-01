package com.recordatoriosMedicamentos.recordatorios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class emailController {
    @Autowired
    private com.recordatoriosMedicamentos.recordatorios.service.emailService emailService;

     @GetMapping("/newAccount/{email}/{username}")
    public String sendNewAccount(@PathVariable String email, @PathVariable String username) {
        emailService.sendNewAccountEmail(email, username);
        return "Correo de nueva cuenta enviado a " + email;
    }

}
