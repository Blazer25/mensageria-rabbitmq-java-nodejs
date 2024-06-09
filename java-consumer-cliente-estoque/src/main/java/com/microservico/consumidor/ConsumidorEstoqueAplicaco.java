package com.microservico.consumidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal para iniciar o aplicativo ConsumidorEstoqueAplicaco.
 */
@SpringBootApplication
public class ConsumidorEstoqueAplicaco {

    /**
     * Método main para iniciar o aplicativo Spring Boot.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args){
        SpringApplication.run(ConsumidorEstoqueAplicaco.class, args);
    }
}