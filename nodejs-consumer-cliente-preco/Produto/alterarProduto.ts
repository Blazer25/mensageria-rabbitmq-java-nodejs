import * as fs from "fs";

/**
 * Interface para representar os dados necessários para alterar um produto.
 */
interface dadosAlterarProduto {
  index: number;
  preco: number;
}

/**
 * Altera o preço de um produto específico em um arquivo JSON.
 *
 * @param {dadosAlterarProduto} dadosAlterar - Os dados necessários para alterar o produto.
 * @returns {void}
 */
export function alterarProduto(dadosAlterar: dadosAlterarProduto): void {
  const caminhoArquivo: string = "./Produto/produtos.json";
  const { index, preco } = dadosAlterar;

  // Lê o arquivo JSON de produtos
  const produtosJson = fs.readFileSync(caminhoArquivo, "utf8");
  const produtos = JSON.parse(produtosJson || "[]");

  // Altera o preço do produto no índice especificado
  produtos[index].preco = preco;

  // Escreve o arquivo JSON de volta
  fs.writeFileSync(caminhoArquivo, JSON.stringify(produtos, null, 2), "utf8");

  console.log(`Preço do produto ${produtos[index].nome} foi alterado!`);
}
