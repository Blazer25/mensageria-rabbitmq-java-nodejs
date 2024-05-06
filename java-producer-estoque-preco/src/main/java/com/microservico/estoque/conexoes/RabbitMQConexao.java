package com.microservico.estoque.conexoes;

import consts.RabbitMQConsts;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

/**
* Classe que representa a conexão com o RabbitMQ para gerenciar filas, trocas e ligações.
 * <br><br>
 * É utilizado como um Component, então é auto gerenciável pelo Spring.
*/
@Component
public class RabbitMQConexao {
    private static final String NOME_EXCHANGE = "amq.direct";

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
        return  new Queue(nomeFila, true, false, false);
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
        return new Binding (fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    /**
     * Método executado após a construção do objeto, que configura as filas, trocas e ligações necessárias.
     * <br><br>
     * É chamado assim que a aplicação inicia, utilizando o PostConstructor do Spring.
     */
    @PostConstruct
    private void adicionar() {
        Queue filaEstoque = this.fila(RabbitMQConsts.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConsts.FILA_PRECO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaEstoque, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
