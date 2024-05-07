package com.microservico.consumidor.config;

import com.microservico.consumidor.excecoes.ErroTratamento;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para a conexão com o RabbitMQ.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Cria uma fábrica de contêiner para ouvintes RabbitMQ.
     *
     * @param connectionFactory A fábrica de conexão RabbitMQ.
     * @return A fábrica de conteiner para ouvintes RabbitMQ.
     */
    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(new ErroTratamento());
        return factory;
    }
}
