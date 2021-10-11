package com.example.gerenciaviagens.bean;



import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Viagem implements Parcelable {
    private long id;
    private Pessoa pessoa;
    private String titulo;
    private int tot_viajantes;
    private int duracao;
    private float custo_total;
    private float custo_pessoa;
    private boolean gasolina;
    private boolean tarifa_aerea;
    private boolean hospedagem;
    private boolean refeicoes;


    private boolean entretenimento;


    protected Viagem(Parcel in) {
        id = in.readLong();
        titulo = in.readString();
        tot_viajantes = in.readInt();
        duracao = in.readInt();
        custo_total = in.readFloat();
        custo_pessoa = in.readFloat();
        gasolina = in.readByte() != 0;
        tarifa_aerea = in.readByte() != 0;
        hospedagem = in.readByte() != 0;
        refeicoes = in.readByte() != 0;
        entretenimento = in.readByte() != 0;




    }

    public static final Creator<Viagem> CREATOR = new Creator<Viagem>() {
        @Override
        public Viagem createFromParcel(Parcel in) {
            return new Viagem(in);
        }

        @Override
        public Viagem[] newArray(int size) {
            return new Viagem[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(titulo);
        dest.writeInt(tot_viajantes);
        dest.writeInt(duracao);
        dest.writeFloat(custo_total);
        dest.writeFloat(custo_pessoa);
        dest.writeByte((byte) (gasolina ? 1 : 0));
        dest.writeByte((byte) (tarifa_aerea ? 1 : 0));
        dest.writeByte((byte) (hospedagem ? 1 : 0));
        dest.writeByte((byte) (refeicoes ? 1 : 0));


        dest.writeByte((byte) (entretenimento ? 1 : 0));

    }

    public static boolean verificaUltimo(final int i, final Viagem v){
        boolean resp = false;
        switch (i){
            case 1:
                if (v.isTarifa_aerea()||v.isHospedagem()||v.isRefeicoes()||v.isEntretenimento()){
                    resp = false;
                }else{
                    resp = true;
                }

                break;
            case 2:
                if (v.isHospedagem()||v.isRefeicoes()||v.isEntretenimento()){
                    resp = false;
                }else{
                    resp = true;
                }


                break;
            case 3:
                if (v.isRefeicoes()||v.isEntretenimento()){
                    resp = false;
                }else{
                    resp = true;
                }




                break;
            case 4:
                if (v.isEntretenimento()){
                    resp = false;
                }else{
                    resp = true;
                }



                break;

        }

        return resp;
    }
}
