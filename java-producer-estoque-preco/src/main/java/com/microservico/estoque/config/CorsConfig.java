package com.microservico.estoque.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração para habilitar CORS (Cross-Origin Resource Sharing) na aplicação.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Adiciona configurações de CORS para permitir solicitações de diferentes origens.
     *
     * @param registry O registro de configurações de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir solicitações de todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Todos os cabeçalhos permitidos
    }
}
