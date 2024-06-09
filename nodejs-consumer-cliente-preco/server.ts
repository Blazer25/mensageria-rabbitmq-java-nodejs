const express = require("express");
import rotas from "./Rotas/rotas";
const cors = require('cors');
import { conectar } from "./RabbitMQ/conexao";

const app = express();
const port = 4000;
// Habilita o CORS para todas as rotas
app.use(cors());

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

    //Se conecta com o RabbitMQ
    conectar();
  } catch (error) {
    console.log(error);
    throw error;
  }
});
