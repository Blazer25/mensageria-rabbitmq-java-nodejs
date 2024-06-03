const express = require("express");
import rotas from "./Rotas/rotas";
import { conectar } from "./RabbitMQ/conexao";

const app = express();
const port = 4000;

/**
 * Middleware para usar as rotas definidas no arquivo ./Rotas/rotas.
 */
app.use("/", rotas);

/**
 * Inicia o servidor na porta especificada.
 *
 * @param {number} port - A porta na qual o servidor vai rodar.
 * @returns {void}
 */
app.listen(port, () => {
  try {
    console.log(`Servidor rodando na porta ${port}`);
    conectar();
  } catch (error) {
    console.log(error);
    throw error;
  }
});
