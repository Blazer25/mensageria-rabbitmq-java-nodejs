package dto;

import java.io.Serializable;

/**
 * Classe que representa os dados de estoque de um produto.
 * <br><br>
 * Os dados aqui são convertidos em bytes no formato base64 (implementação do Serializable).
 */
public class EstoqueDTO implements Serializable {
    public String codigoProduto;
    public int quantidade;
}
