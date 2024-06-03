import * as fs from "fs";

/**
 * Interface para representar um produto sem nome.
 */
interface ProdutoSemNome {
  codigoProduto: string;
  preco: number;
}

/**
 * Salva um produto sem nome no arquivo JSON. 
 * Se o arquivo já existir, adiciona o produto ao array existente. 
 * Caso contrário, cria um novo arquivo com o produto.
 * 
 * @param {ProdutoSemNome} produtoSemNome - O produto a ser salvo.
 * @returns {void}
 */
export function salvarProduto(produtoSemNome: ProdutoSemNome): void {
  const caminhoArquivo: string = "./Produto/produtosSemNome.json";

  // Verifica se o arquivo já existe
  if (fs.existsSync(caminhoArquivo)) {
    const dadosJSON = fs.readFileSync(caminhoArquivo, "utf8");
    let dados = JSON.parse(dadosJSON || "[]");

    // Adiciona o novo produto ao array existente
    Array.isArray(dados) ? dados.push(produtoSemNome) : (dados = produtoSemNome);

    const dadosAtualizados = JSON.stringify(dados, null, 2);

    // Escreve os dados atualizados de volta no arquivo
    fs.writeFileSync(caminhoArquivo, dadosAtualizados);

    console.log("Dados adicionados ao array no arquivo", caminhoArquivo);
  } else {
    console.log(
      "O arquivo",
      caminhoArquivo,
      "não existe. Criando novo arquivo..."
    );

    const dados = [produtoSemNome];

    const dadosJSON = JSON.stringify(dados, null, 2);

    // Cria um novo arquivo com o produto
    fs.writeFileSync(caminhoArquivo, dadosJSON);

    console.log("Novo arquivo criado com os dados do produto selecionado");
  }
}
