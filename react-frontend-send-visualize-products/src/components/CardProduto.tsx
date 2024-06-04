import "./CardProduto.css";
import React from "react";
import { Produto, ProdutoSemNome } from "../types";

interface CardProdutoProps {
  produtos: (Produto | ProdutoSemNome)[];
}

function retornarValorComDuasCadasDecimais(valor: number): string {
  return valor.toFixed(2);
}

const CardProduto: React.FC<CardProdutoProps> = ({ produtos }) => {
  return (
    <>
      {produtos.map((produto) => (
        <div className="card" key={produto.codigoProduto}>
          <p>{produto.codigoProduto}</p>
          {"nome" in produto && <p>{produto.nome}</p>}
          <p>R$ {retornarValorComDuasCadasDecimais(produto.preco)}</p>
        </div>
      ))}
    </>
  );
};

export default CardProduto;
