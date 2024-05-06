package com.microservico.consumidor.consumidor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import consts.RabbitMQConsts;
import dto.EstoqueDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorEstoque {

    @RabbitListener(queues = RabbitMQConsts.FILA_ESTOQUE)
    private void consumidor(String mensagem) throws JsonProcessingException, InterruptedException {
        EstoqueDTO estoqueDTO = new ObjectMapper().readValue(mensagem, EstoqueDTO.class);

        System.out.println("Código do produto: " + estoqueDTO.codigoProduto);
        System.out.println("Quantidade: " + estoqueDTO.quantidade);
        System.out.println("¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨");

//        throw new IllegalArgumentException("Argumento inválido!");
    }
}
