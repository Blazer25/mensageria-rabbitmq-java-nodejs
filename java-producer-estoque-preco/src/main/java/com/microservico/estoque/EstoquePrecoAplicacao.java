package com.microservico.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal para iniciar o aplicativo EstoquePrecoAplicacao.
 */
@SpringBootApplication
public class EstoquePrecoAplicacao {

    /**
     * Método main para iniciar o aplicativo Spring Boot.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args){
        SpringApplication.run(EstoquePrecoAplicacao.class, args);
    }
}