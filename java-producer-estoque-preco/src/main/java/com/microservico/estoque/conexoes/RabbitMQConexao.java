package com.microservico.estoque.conexoes;

import consts.RabbitMQConsts;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa a conexão com o RabbitMQ para gerenciar filas, trocas e ligações.
 * <br><br>
 * É utilizado como um Component, então é auto gerenciável pelo Spring.
 */
@Component
public class RabbitMQConexao {
    private static final String NOME_EXCHANGE = "amq.direct";
    private static final String NOME_EXCHANGE_DLX = "dlx_exchange";

    private final AmqpAdmin amqpAdmin;

    /**
     * Construtor da classe RabbitMQConexao.
     * @param amqpAdmin O administrador AMQP para gerenciar as operações do RabbitMQ.
     */
    public RabbitMQConexao(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    /**
     * Cria a instância de uma fila.
     * @param nomeFila representa o nome da fila a ser criada.
     * @return A instância da fila criada.
     */
    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    /**
     * Cria a instância de uma fila com configurações adicionais.
     * @param nomeFila representa o nome da fila a ser criada.
     * @param argumentos configurações adicionais para a fila.
     * @return A instância da fila criada.
     */
    private Queue filaComArgumentos(String nomeFila, Map<String, Object> argumentos) {
        return new Queue(nomeFila, true, false, false, argumentos);
    }

    /**
     * Cria uma nova troca direta.
     * @return A troca direta criada.
     */
    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    /**
     * Cria uma ligação entre uma fila e uma troca.
     *
     * @param fila A fila a ser ligada.
     * @param troca A troca à qual a fila será ligada.
     * @return A instância da ligação criada.
     */
    private Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    /**
     * Método executado após a construção do objeto, que configura as filas, trocas e ligações necessárias.
     * <br><br>
     * É chamado assim que a aplicação inicia, utilizando o PostConstructor do Spring.
     */
    @PostConstruct
    private void adicionar() {
        // Limpar as filas (se necessário)
        // this.amqpAdmin.deleteQueue(RabbitMQConsts.FILA_ESTOQUE);
        // this.amqpAdmin.deleteQueue(RabbitMQConsts.FILA_PRECO);
        // this.amqpAdmin.deleteQueue(RabbitMQConsts.FILA_MORTA);

        Queue filaMorta = this.fila(RabbitMQConsts.FILA_MORTA);

        Map<String, Object> argumentosEstoque = new HashMap<>();
//        argumentosEstoque.put("x-message-ttl", 30000);
        argumentosEstoque.put("x-dead-letter-exchange", NOME_EXCHANGE);
        argumentosEstoque.put("x-dead-letter-routing-key", RabbitMQConsts.FILA_MORTA);

        Map<String, Object> argumentosPreco = new HashMap<>();
        argumentosPreco.put("x-dead-letter-exchange", NOME_EXCHANGE);
        argumentosPreco.put("x-dead-letter-routing-key", RabbitMQConsts.FILA_MORTA);

        Queue filaEstoque = this.filaComArgumentos(RabbitMQConsts.FILA_ESTOQUE, argumentosEstoque);
        Queue filaPreco = this.filaComArgumentos(RabbitMQConsts.FILA_PRECO, argumentosPreco);

        DirectExchange troca = this.trocaDireta();
        DirectExchange trocaDLX = new DirectExchange(NOME_EXCHANGE_DLX);

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);
        Binding ligacaoFilaMorta = this.relacionamento(filaMorta, trocaDLX);

        this.amqpAdmin.declareQueue(filaMorta);
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareExchange(trocaDLX);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
        this.amqpAdmin.declareBinding(ligacaoFilaMorta);
    }
}
