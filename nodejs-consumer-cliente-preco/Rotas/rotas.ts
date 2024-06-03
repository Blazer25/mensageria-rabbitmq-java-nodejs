import { Router } from "express";

const rotas = Router();

rotas.get("/", (req, res) => {
  res.send("Servidor online :)");
});

rotas.get("/produtos", (req, res) => {
  res.send("Produtos :)");
});

rotas.get("/produtos-sem-nome", (req, res) => {
  res.send("Produtos sem nome :)");
});

export default rotas;
