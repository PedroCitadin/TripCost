package com.example.gerenciaviagens.bean;

public class Viagem {
    private long id;
    private Pessoa pessoa;
    private String titulo;
    private int tot_viajantes;
    private int duracao;
    private float custo_total;
    private float custo_pessoa;
    private boolean entretenimento;
    private boolean refeicoes;
    private boolean gasolina;
    private boolean hospedagem;
    private boolean tarifa_aerea;

    public boolean isEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(boolean entretenimento) {
        this.entretenimento = entretenimento;
    }

    public boolean isRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(boolean refeicoes) {
        this.refeicoes = refeicoes;
    }

    public boolean isGasolina() {
        return gasolina;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public boolean isHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(boolean hospedagem) {
        this.hospedagem = hospedagem;
    }

    public boolean isTarifa_aerea() {
        return tarifa_aerea;
    }

    public void setTarifa_aerea(boolean tarifa_aerea) {
        this.tarifa_aerea = tarifa_aerea;
    }

    public Viagem() {
    }

    public Viagem(long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTot_viajantes() {
        return tot_viajantes;
    }

    public void setTot_viajantes(int tot_viajantes) {
        this.tot_viajantes = tot_viajantes;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public float getCusto_total() {
        return custo_total;
    }

    public void setCusto_total(float custo_total) {
        this.custo_total = custo_total;
    }

    public float getCusto_pessoa() {
        return custo_pessoa;
    }

    public void setCusto_pessoa(float custo_pessoa) {
        this.custo_pessoa = custo_pessoa;
    }

    public static boolean converterInt(int i){
        return i ==1;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
