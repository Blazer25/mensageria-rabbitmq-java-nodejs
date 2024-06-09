package com.microservico.consumidor.excecoes;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

/**
 * Classe para tratamento de erros durante a execução de listeners RabbitMQ.
 */
public class ErroTratamento implements ErrorHandler {

    /**
     * Método para lidar com erros durante a execução de listeners RabbitMQ.
     * @param t A exceção que ocorreu durante a execução.
     * @throws AmqpRejectAndDontRequeueException Uma exceção para indicar que a mensagem não deve ser reenfileirada.
     */
    @Override
    public void handleError(Throwable t) {
        String nomeDaFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(nomeDaFila);

        String mensagem = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(mensagem);

        System.out.println(t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila!");
    }
}