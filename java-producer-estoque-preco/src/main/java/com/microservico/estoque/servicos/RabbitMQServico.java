package com.microservico.estoque.servicos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe do serviço do RabbitMQ.
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
            this.rabbitTemplate.convertAndSend(nomeFila, msgJSON);
            System.out.println(retornarDataHoraAtual() + " Mensagem enviada...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna a data e hora atual formatada como uma string.
     *
     * @return Uma string contendo a data e hora atual no formato "dd/MM/yyyy HH:mm:ss".
     */
    public String retornarDataHoraAtual() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = agora.format(formato);

        return dataHoraFormatada;
    }

}
