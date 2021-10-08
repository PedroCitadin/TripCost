package com.example.gerenciaviagens.bean;

public class Entretenimento {
    private long id;
    private Viagem viagem;
    private String descricao;
    private float valor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
