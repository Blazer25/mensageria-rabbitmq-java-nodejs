package com.microservico.estoque.config;

import consts.RabbitMQConsts;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para a configuração do RabbitMQ.
 */
@Configuration
public class RabbitMQConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * Método para criar e configurar o RabbitTemplate para a comunicação com o RabbitMQ.
     * @return RabbitTemplate configurado.
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                String messageId = correlationData != null ? correlationData.getId() : "";
                System.err.println("Erro ao enviar mensagem com ID: " + messageId);
                enviarParaFilaMorta(correlationData.getReturned().getMessage());
            }
        });
        return template;
    }

    /**
     * Método privado para enviar mensagens para a fila morta após um erro no envio.
     * @param message A mensagem que deve ser enviada para a fila morta.
     */
    private void enviarParaFilaMorta(Message message) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.send(RabbitMQConsts.FILA_MORTA, message);
        System.out.println("Mensagem enviada para a fila morta após erro.");
    }
}
