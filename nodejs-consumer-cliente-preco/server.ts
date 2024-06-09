const express = require("express");
import rotas from "./Rotas/rotas";
const cors = require("cors");
import { conectar } from "./RabbitMQ/conexao";
import configureSwagger from "./swaggerConfig";

const app = express();
const port = 4000;
// Habilita o CORS para todas as rotas
app.use(cors());

//Usando o swagger (documentação)
configureSwagger(app);

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
    console.log(`Servidor rodando na  rota http://localhost:${port}`);
    console.log(
      `Documentação rotando na rota http://localhost:${port}/api-docs`
    );

    //Se conecta com o RabbitMQ
    conectar();
  } catch (error) {
    console.log(error);
    throw error;
  }
});
