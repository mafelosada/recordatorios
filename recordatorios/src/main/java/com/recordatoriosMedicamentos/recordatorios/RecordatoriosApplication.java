package com.recordatoriosMedicamentos.recordatorios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.recordatoriosMedicamentos.recordatorios")
public class RecordatoriosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordatoriosApplication.class, args);
	}

}
