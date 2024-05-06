package com.microservico.estoque.controller;

import consts.RabbitMQConsts;
import dto.PrecoDTO;
import com.microservico.estoque.servicos.RabbitMQServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe de controle de preço
 */
@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    @Autowired
    private RabbitMQServico rabbitMQServico;

    /**
     * Método para alterar o preço de um produto.
     *
     * @param precoDTO O objeto PrecoDTO contendo os dados de preço a serem alterados.
     * @return ResponseEntity com status HTTP OK se a operação for bem-sucedida.
     */
    @PutMapping
    private ResponseEntity alterarPreco(@RequestBody PrecoDTO precoDTO) {
        final String NOME_FILA_PRECO = RabbitMQConsts.FILA_PRECO;
        this.rabbitMQServico.enviarMensagem(NOME_FILA_PRECO, precoDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
