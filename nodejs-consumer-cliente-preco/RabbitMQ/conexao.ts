import { connect } from "amqplib/callback_api";
import { verificarProduto } from "../Produto/verificarProduto";

/**
 * Conecta ao RabbitMQ e consome mensagens da fila de PRECO.
 * @returns {void}
 */
export function conectar(): void {
  const fila: string = "PRECO";

  console.log("Conectando ao RabbitMQ...");

  connect(
    {
      hostname: "localhost",
      port: 5672,
      username: "admin",
      password: "123456",
    },
    (erro, conexao) => {
      if (erro) {
        console.log("Erro ao conectar: ", erro);
        throw erro;
      }

      conexao.createChannel((erroCanal, canal) => {
        if (erroCanal) {
          console.log(erroCanal);
          throw erroCanal;
        }
        console.log("Canal criado com sucesso!");

        canal.consume(
          fila,
          (mensagem: any) => {
            if (mensagem) {
              const dadosString = mensagem.content.toString();
              try {
                const dadosJSON = JSON.parse(dadosString);
                verificarProduto(dadosJSON);
              } catch (error) {
                console.error("Erro ao fazer o parsing dos dados JSON:", error);
                throw error;
              }
            }
          },
          { noAck: true }
        );
      });
    }
  );
}
