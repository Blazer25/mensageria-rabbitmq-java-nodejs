import { Application } from "express";
import * as swaggerUi from "swagger-ui-express";
import * as YAML from "yamljs";
import * as path from "path";

/**
 * Carrega o arquivo YAML OpenAPI para configurar o Swagger UI.
 * @returns {Object} O documento YAML carregado.
 */
const swaggerDocument = YAML.load(
  path.join(__dirname, "../documentacao/openapi.yaml")
);

/**
 * Configura o Swagger UI para a aplicação Express (cria sua rota).
 * @param {Application} app - A aplicação Express.
 */
const configureSwagger = (app: Application): void => {
  app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerDocument));
};

export default configureSwagger;
