import React from "react";
import "./CardProduto.css";

interface Produto {
  id: string;
  nome: string;
  preco: number;
}

interface CardProdutoProps {
  produtos: Produto[];
}

function retornarValorComDuasCadasDecimais(valor: number): string {
  return valor.toFixed(2);
}

const CardProduto: React.FC<CardProdutoProps> = ({ produtos }) => {
  return (
    <div>
      {produtos.map((item) => (
        <div className="card" key={item.id}>
          <p>{item.id}</p>
          {item.nome && <p>{item.nome}</p>}
          <p>R$ {retornarValorComDuasCadasDecimais(item.preco)}</p>
        </div>
      ))}
    </div>
  );
};

export default CardProduto;
