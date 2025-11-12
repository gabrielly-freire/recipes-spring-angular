package imd.ufrn.foodrecipes.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ReceitaRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String categoria;

    @NotNull
    @Positive
    private Integer tempoPreparo;

    @NotNull
    @Positive
    private Integer porcoes;

    @NotBlank
    @Size(max = 4000)
    private String instrucoes;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<IngredienteRequest> ingredientes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Integer tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Integer getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(Integer porcoes) {
        this.porcoes = porcoes;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public List<IngredienteRequest> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteRequest> ingredientes) {
        this.ingredientes = ingredientes;
    }
}