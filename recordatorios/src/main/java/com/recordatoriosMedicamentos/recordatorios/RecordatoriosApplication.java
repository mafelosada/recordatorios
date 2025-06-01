package com.recordatoriosMedicamentos.recordatorios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.recordatoriosMedicamentos.recordatorios")
@EnableScheduling
public class RecordatoriosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordatoriosApplication.class, args);
	}

}
