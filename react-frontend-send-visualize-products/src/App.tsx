// import { useState } from 'react'
import "./App.css";
import CardProduto from "./components/CardProduto";

const teste = [
  {
    id: "9018d5df-d43a-4c61-ad71-84da1ca9ec66",
    nome: "teste1",
    preco: 10,
  },
  {
    id: "9018d5df-d43a-4c61-ad71-84da1ca9ec66",
    nome: "",
    preco: 20,
  },
];

function App() {
  return (
    <div className="container">
      <div>
        <span>Produtos com nome</span>
        <CardProduto produtos={teste} />
      </div>
      <div>
        <span>Produtos sem nome</span>

        <CardProduto produtos={teste} />
      </div>
    </div>
  );
}

export default App;
