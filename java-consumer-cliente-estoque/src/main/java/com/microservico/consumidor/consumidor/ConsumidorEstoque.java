package com.microservico.consumidor.consumidor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import consts.RabbitMQConsts;
import dto.EstoqueDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por consumir mensagens relacionadas ao consumidor e o estoque.
 */
@Component
public class ConsumidorEstoque {

    /**
     * Processa uma mensagem recebida pelo consumidor.
     *
     * @param mensagem A mensagem recebida pelo consumidor.
     * @throws JsonProcessingException Se ocorrer um erro ao processar a mensagem JSON.
     */
    @RabbitListener(queues = RabbitMQConsts.FILA_ESTOQUE)
    private void consumidor(String mensagem) throws JsonProcessingException {
        EstoqueDTO estoqueDTO = new ObjectMapper().readValue(mensagem, EstoqueDTO.class);

        System.out.println(retornarDataHoraAtual() + " Mensagem recebida...");

        System.out.println("Produto: " + estoqueDTO.getCodigoProduto());
        System.out.println("Quantidade: " + estoqueDTO.getQuantidade());
        System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");
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
