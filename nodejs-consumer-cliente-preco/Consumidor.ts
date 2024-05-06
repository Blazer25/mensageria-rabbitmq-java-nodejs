import { connect } from "amqplib/callback_api";
import * as fs from "fs";

const fila: string = "PRECO";
const caminhoArquivo: string = "dados.json";

function salvarMensagens(dadosNovos: any[]): void {
  if (fs.existsSync(caminhoArquivo)) {
    const dadosJSON = fs.readFileSync(caminhoArquivo, "utf8");
    const dados = JSON.parse(dadosJSON || "{}");

    Array.isArray(dados.array)
      ? dados.array.push(...dadosNovos)
      : (dados.array = dadosNovos);

    const dadosAtualizados = JSON.stringify(dados, null, 2);

    fs.writeFileSync(caminhoArquivo, dadosAtualizados);

    console.log("Dados adicionados ao array no arquivo", caminhoArquivo);
  } else {
    console.log(
      "O arquivo",
      caminhoArquivo,
      "não existe. Criando novo arquivo..."
    );

    const dados = { array: dadosNovos };

    const dadosJSON = JSON.stringify(dados, null, 2);

    fs.writeFileSync(caminhoArquivo, dadosJSON);

    console.log("Novo arquivo criado com o primeiro dado");
  }
}

function conectar(): void {
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
        return;
      }

      conexao.createChannel((erroCanal, canal) => {
        if (erroCanal) {
          console.log(erroCanal);
          return;
        }
        console.log("Canal criado com sucesso!");

        canal.consume(
          fila,
          (mensagem: any) => {
            if (mensagem) {
              const dadosString = mensagem.content.toString();
              try {
                const dadosJSON = JSON.parse(dadosString);
                salvarMensagens([dadosJSON]);
              } catch (error) {
                console.error("Erro ao fazer o parsing dos dados JSON:", error);
              }
            }
          },
          { noAck: true }
        );
      });
    }
  );
}

conectar();
