import * as fs from "fs";
import { salvarProduto } from "./salvarProduto";
import { alterarProduto } from "./alterarProduto";

/**
 * Interface para representar um produto.
 */
interface Produto {
  id: string;
  nome: string;
  preco: number;
}

/**
 * Interface para representar um produto sem nome.
 */
interface ProdutoSemNome {
  codigoProduto: string;
  preco: number;
}

/**
 * Verifica se um produto existe no arquivo JSON de produtos. 
 * Se o produto não for encontrado, ele será salvo. 
 * Se for encontrado, o preço será alterado.
 * 
 * @param {ProdutoSemNome} produtoSemNome - O produto a ser verificado.
 * @returns {void}
 */
export function verificarProduto(produtoSemNome: ProdutoSemNome): void {
  const caminhoArquivo: string = "./Produto/produtos.json";

  // Lê o arquivo JSON de produtos
  const dadosJSON = fs.readFileSync(caminhoArquivo, "utf8");
  const dadosProdutos = JSON.parse(dadosJSON || "[]");

  // Encontra o índice do produto com o ID fornecido
  const indexProduto = dadosProdutos.findIndex(
    (produto: Produto) => produto.id === produtoSemNome.codigoProduto
  );

  // Se o produto não for encontrado, salva o novo produto
  if (indexProduto === -1) {
    console.log(
      "Produto não encontrado. Salvando ID e Preço do produto passado!"
    );
    salvarProduto(produtoSemNome);
    return;
  }

  // Se o produto for encontrado, altera o preço do produto existente
  console.log("Produto encontrado. Alterando Preço do produto passado!");
  alterarProduto({ index: indexProduto, preco: produtoSemNome.preco });
}
