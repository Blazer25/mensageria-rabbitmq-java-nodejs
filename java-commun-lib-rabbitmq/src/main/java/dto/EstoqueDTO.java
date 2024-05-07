package dto;

import java.io.Serializable;

/**
 * Classe que representa os dados de estoque de um produto.
 * <br><br>
 * Os dados aqui são convertidos em bytes no formato base64 (implementação do Serializable).
 */
public class EstoqueDTO implements Serializable {
    public String codigoProduto;
    public Integer quantidade;

    public EstoqueDTO(String codigoProduto, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    /**
     * Valida se o código do produto é uma string.
     * @throws IllegalArgumentException Se o código do produto não for uma string.
     */
    public void validarCodigoProduto() {
        if (!(codigoProduto instanceof String)) {
            throw new IllegalArgumentException("O código do produto deve ser do tipo texto.");
        }
    }

    /**
     * Valida se a quantidade é um número inteiro positivo.
     * @throws IllegalArgumentException Se a quantidade não for um número inteiro positivo.
     */
    public void validarQuantidade() {
        if (!(quantidade instanceof Integer)) {
            throw new IllegalArgumentException("A quantidade deve ser do tipo inteiro.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número inteiro positivo.");
        }
    }

    // Getters e setters
}
