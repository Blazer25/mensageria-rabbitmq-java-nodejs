package dto;

import java.io.Serializable;

/**
 * Classe que representa os dados de estoque de um produto.
 * Implementa Serializable para permitir a conversão de seus dados em bytes no formato base64.
 */
public class EstoqueDTO implements Serializable {
    private String codigoProduto;
    private Integer quantidade;

    /**
     * Construtor padrão sem argumentos.
     */
    public EstoqueDTO() {
    }

    /**
     * Construtor com argumentos.
     *
     * @param codigoProduto o código do produto, que deve ser um UUIDv4.
     * @param quantidade a quantidade do produto em estoque, que deve ser um número inteiro positivo.
     */
    public EstoqueDTO(String codigoProduto, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    /**
     * Obtém o código do produto.
     *
     * @return o código do produto.
     */
    public String getCodigoProduto() {
        return this.codigoProduto;
    }

    /**
     * Define o código do produto.
     *
     * @param codigoProduto o código do produto, que deve ser um UUIDv4.
     */
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * Obtém a quantidade do produto em estoque.
     *
     * @return a quantidade do produto em estoque.
     */
    public Integer getQuantidade() {
        return this.quantidade;
    }

    /**
     * Define a quantidade do produto em estoque.
     *
     * @param quantidade a quantidade do produto em estoque, que deve ser um número inteiro positivo.
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Valida se o código do produto é um UUIDv4 válido.
     *
     * @throws IllegalArgumentException se o código do produto for nulo, vazio ou não for um UUIDv4 válido.
     */
    public void validarCodigoProduto() {
        if (this.codigoProduto == null || this.codigoProduto.isEmpty()) {
            throw new IllegalArgumentException("O código do produto deve ser um UUIDv4 e não pode estar vazio.");
        }

        String uuidv4Pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
        if (!this.codigoProduto.matches(uuidv4Pattern)) {
            throw new IllegalArgumentException("O código do produto deve ser um UUIDv4 válido.");
        }
    }

    /**
     * Valida se a quantidade é um número inteiro positivo.
     *
     * @throws IllegalArgumentException se a quantidade for nula, não for um número ou for um número não positivo.
     */
    public void validarQuantidade() {
        if (this.quantidade == null) {
            throw new IllegalArgumentException("A quantidade não pode ser nula.");
        }

        try {
            int valorQuantidade = Integer.parseInt(String.valueOf(this.quantidade));
            if (valorQuantidade <= 0) {
                throw new IllegalArgumentException("A quantidade deve ser um número inteiro positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A quantidade deve ser um número inteiro positivo.");
        }
    }

    /**
     * Valida todos os campos do DTO.
     *
     * @throws IllegalArgumentException se qualquer campo for inválido.
     */
    public void validar() {
        this.validarCodigoProduto();
        this.validarQuantidade();
    }
}
