package com.example.gerenciaviagens.bean;

public class Hospedagem {
    private long id;
    private Viagem viagem;
    private float custo_medio;
    private int tot_noites;
    private int tot_quartos;
    private float tot_custo;

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

    public float getCusto_medio() {
        return custo_medio;
    }

    public void setCusto_medio(float custo_medio) {
        this.custo_medio = custo_medio;
    }

    public int getTot_noites() {
        return tot_noites;
    }

    public void setTot_noites(int tot_noites) {
        this.tot_noites = tot_noites;
    }

    public int getTot_quartos() {
        return tot_quartos;
    }

    public void setTot_quartos(int tot_quartos) {
        this.tot_quartos = tot_quartos;
    }

    public float getTot_custo() {
        return tot_custo;
    }

    public void setTot_custo(float tot_custo) {
        this.tot_custo = tot_custo;
    }
}
