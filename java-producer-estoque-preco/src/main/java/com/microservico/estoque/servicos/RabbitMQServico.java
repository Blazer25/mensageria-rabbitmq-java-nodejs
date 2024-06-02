package com.microservico.estoque.servicos;

import com.fasterxml.jackson.databind.ObjectMapper;
import consts.RabbitMQConsts;
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
        String msgJSON = null;
        try {
            msgJSON = this.objectMapper.writeValueAsString(mensagem);
//            if (true) {
//                throw new RuntimeException("Erro genérico ao realizar disparo de mensagem!");
//            }
            this.rabbitTemplate.convertAndSend(nomeFila, msgJSON);
            System.out.println(retornarDataHoraAtual() + " Mensagem enviada para a fila principal: " + nomeFila);
        } catch (Exception e) {
            System.err.println(retornarDataHoraAtual() + " Erro ao enviar mensagem para a fila principal: " + nomeFila);
            if (msgJSON != null) {
                enviarParaFilaMorta(msgJSON);
            } else {
                System.err.println(retornarDataHoraAtual() + " Erro ao serializar a mensagem para a fila morta.");
            }
        }
    }

    /**
     * Retorna a data e hora atual formatada como uma string.
     *
     * @return Uma string contendo a data e hora atual no formato "dd/MM/yyyy HH:mm:ss".
     */
    private void enviarParaFilaMorta(String mensagem) {
        try {
            this.rabbitTemplate.convertAndSend( RabbitMQConsts.FILA_MORTA, mensagem);
            System.out.println(retornarDataHoraAtual() + " Mensagem enviada para a fila morta...");
        } catch (Exception ex) {
            System.err.println(retornarDataHoraAtual() + " Erro ao enviar mensagem para a fila morta: " + ex.getMessage());
        }
    }

    public String retornarDataHoraAtual() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return agora.format(formato);
    }

}
