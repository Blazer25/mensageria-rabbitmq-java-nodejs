import { useEffect } from "react";
import "./App.css";
import CardProduto from "./components/CardProduto";
import { useFetch } from "./hooks/useFetch";
import { Produto, ProdutoSemNome } from "./types";

function App() {
  const {
    data: produtosComNome,
    error: errorComNome,
    refetch: refetchProdutosComNome,
  } = useFetch<Produto[]>("http://localhost:4000/produtos");
  const {
    data: produtosSemNome,
    error: errorSemNome,
    refetch: refetchProdutosSemNome,
  } = useFetch<ProdutoSemNome[]>("http://localhost:4000/produtos-sem-nome");

  useEffect(() => {
    const handleVisibilityChange = () => {
      if (document.visibilityState === "visible") {
        refetchProdutosComNome();
        refetchProdutosSemNome();
      }
    };

    const handleFocus = () => {
      refetchProdutosComNome();
      refetchProdutosSemNome();
    };

    document.addEventListener("visibilitychange", handleVisibilityChange);
    window.addEventListener("focus", handleFocus);

    return () => {
      document.removeEventListener("visibilitychange", handleVisibilityChange);
      window.removeEventListener("focus", handleFocus);
    };
  }, [refetchProdutosComNome, refetchProdutosSemNome]);

  return (
    <div className="container">
      {errorComNome && <p>{errorComNome}</p>}
      <div>
        <h2>Produtos com nome</h2>
        {produtosComNome && <CardProduto produtos={produtosComNome} />}
        {!produtosComNome?.length && <p>Nenhum produto com nome encontrado</p>}
      </div>
      {errorSemNome && <p>{errorSemNome}</p>}
      <div>
        <h2>Produtos sem nome</h2>
        {produtosSemNome && <CardProduto produtos={produtosSemNome} />}
        {!produtosSemNome?.length && <p>Nenhum produto sem nome encontrado</p>}
      </div>
    </div>
  );
}

export default App;
