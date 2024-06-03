import * as fs from "fs";
import { Router } from "express";

const rotas = Router();

rotas.get("/", (req, res) => {
  res.send("Servidor online :)");
});

rotas.get("/produtos", (req, res) => {
  const caminhoArquivo: string = "./Produto/produtos.json";
  const dadosJSON = fs.readFileSync(caminhoArquivo, "utf8");
  const dadosProdutos = JSON.parse(dadosJSON || "[]");

  res.status(200).send(dadosProdutos);
});

rotas.get("/produtos-sem-nome", (req, res) => {
  const caminhoArquivo: string = "./Produto/produtosSemNome.json";
  if (fs.existsSync(caminhoArquivo)) {
    const dadosJSON = fs.readFileSync(caminhoArquivo, "utf8");
    const dadosProdutos = JSON.parse(dadosJSON || "[]");

    res.status(200).send(dadosProdutos);
    return;
  }

  res.status(200).send([]);
});

export default rotas;
