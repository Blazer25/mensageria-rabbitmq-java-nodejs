package com.microservico.estoque.controller;

import com.microservico.estoque.servicos.RabbitMQServico;
import consts.RabbitMQConsts;
import dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe de controle de estoque.
 */
@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {
    @Autowired
    private RabbitMQServico rabbitMQServico;

    /**
     * Método para alterar o estoque de um produto.
     *
     * @param estoqueDTO O objeto EstoqueDTO contendo os dados de estoque a serem alterados.
     * @return ResponseEntity com status HTTP OK se a operação for bem-sucedida.
     */
    @PutMapping
    private ResponseEntity alterarEstoque(@RequestBody EstoqueDTO estoqueDTO) {

        try {
            estoqueDTO.validar();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao validar os dados: " + e.getMessage());
        }

        final String NOME_FILA_ESTOQUE = RabbitMQConsts.FILA_ESTOQUE;
        this.rabbitMQServico.enviarMensagem(NOME_FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
