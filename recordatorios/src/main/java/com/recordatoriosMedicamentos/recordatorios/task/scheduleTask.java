package com.recordatoriosMedicamentos.recordatorios.task;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class scheduleTask {

    @Scheduled(fixedRate = 1000) // cada 1 segundo
    public void taskEverySecond() {
        System.out.println("Tarea cada segundo: " + LocalDateTime.now());
    }
    

    @Scheduled(fixedRate = 5000) // cada 5 segundos
    public void taskEveryFiveSeconds() {
        System.out.println("Tarea cada 5 segundos: " + LocalDateTime.now());
    }





}
