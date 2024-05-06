package com.microservico.estoque.servicos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe do serviço do RabbitMQ.
 * <br>
 * Responsável por: enviar as mensagens.
 */
@Service
public class RabbitMQServico {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Método responsável por enviar as mensagens
     * @param nomeFila Nome da fila que a mensagem deve ser enviada.
     * @param mensagem Objeto contendo a mensagem a ser enviada.
     */
    public void enviarMensagem(String nomeFila, Object mensagem) {
        try {
            String msgJSON = this.objectMapper.writeValueAsString(mensagem);
            System.out.println(msgJSON);
            this.rabbitTemplate.convertAndSend(nomeFila, msgJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
