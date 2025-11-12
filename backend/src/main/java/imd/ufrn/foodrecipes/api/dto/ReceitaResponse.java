package imd.ufrn.foodrecipes.api.dto;

import java.util.List;

public class ReceitaResponse {
    private Long id;
    private String nome;
    private String categoria;
    private Integer tempoPreparo;
    private Integer porcoes;
    private String instrucoes;
    private List<IngredienteResponse> ingredientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<IngredienteResponse> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteResponse> ingredientes) {
        this.ingredientes = ingredientes;
    }
}