package imd.ufrn.foodrecipes.api.dto;

import jakarta.validation.constraints.NotBlank;

public class IngredienteRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String quantidade;

    @NotBlank
    private String unidadeMedida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}