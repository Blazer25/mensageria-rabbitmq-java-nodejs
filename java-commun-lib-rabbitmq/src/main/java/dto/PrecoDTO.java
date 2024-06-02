package dto;

import java.io.Serializable;

/**
 * Classe que representa os dados de preço de um produto.
 * <br><br>
 * Os dados aqui são convertidos em bytes no formato base64 (implementação do Serializable).
 */
public class PrecoDTO implements Serializable {
    private String codigoProduto;
    private double preco;

    /**
     * Construtor padrão sem argumentos.
     */
    public PrecoDTO() {
    }

    /**
     * Construtor com argumentos.
     *
     * @param codigoProduto o código do produto, que deve ser um UUIDv4.
     * @param preco o preço do produto, que deve ser um número positivo.
     */
    public PrecoDTO(String codigoProduto, double preco) {
        this.codigoProduto = codigoProduto;
        this.preco = preco;
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
     * Obtém o preço do produto.
     *
     * @return o preço do produto.
     */
    public double getPreco() {
        return this.preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco o preço do produto, que deve ser um número positivo.
     */
    public void setPreco(double preco) {
        this.preco = preco;
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
     * Valida se o preço é um número positivo.
     *
     * @throws IllegalArgumentException se o preço não for um número positivo.
     */
    public void validarPreco() {
        if (this.preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser um número positivo.");
        }
    }

    /**
     * Valida todos os campos do DTO.
     *
     * @throws IllegalArgumentException se qualquer campo for inválido.
     */
    public void validar() {
        validarCodigoProduto();
        validarPreco();
    }
}
