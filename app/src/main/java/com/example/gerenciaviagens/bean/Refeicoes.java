package com.example.gerenciaviagens.bean;

public class Refeicoes {
    private int id;
    private Viagem viagem;
    private float custo_refeicoes;
    private int refeicoes_dia;
    private float tot_custo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public float getCusto_refeicoes() {
        return custo_refeicoes;
    }

    public void setCusto_refeicoes(float custo_refeicoes) {
        this.custo_refeicoes = custo_refeicoes;
    }

    public int getRefeicoes_dia() {
        return refeicoes_dia;
    }

    public void setRefeicoes_dia(int refeicoes_dia) {
        this.refeicoes_dia = refeicoes_dia;
    }

    public float getTot_custo() {
        return tot_custo;
    }

    public void setTot_custo(float tot_custo) {
        this.tot_custo = tot_custo;
    }
}
