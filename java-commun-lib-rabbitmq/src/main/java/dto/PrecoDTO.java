package dto;

import java.io.Serializable;

/**
 * Classe que representa os dados de preço de um produto.
 * <br><br>
 * Os dados aqui são convertidos em bytes no formato base64 (implementação do Serializable).
 */
public class PrecoDTO implements Serializable {
    public String codigoProduto;
    public double preco;

}
