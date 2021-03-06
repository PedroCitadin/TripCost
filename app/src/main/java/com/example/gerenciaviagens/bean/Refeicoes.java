package com.example.gerenciaviagens.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Refeicoes implements Parcelable {
    private long id;
    private Viagem viagem;
    private float custo_refeicoes;
    private int refeicoes_dia;
    private float tot_custo;



    public Refeicoes() {
    }

    public static final Creator<Refeicoes> CREATOR = new Creator<Refeicoes>() {
        @Override
        public Refeicoes createFromParcel(Parcel in) {
            return new Refeicoes(in);
        }

        @Override
        public Refeicoes[] newArray(int size) {
            return new Refeicoes[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
       // dest.writeParcelable(viagem, flags);
        dest.writeFloat(custo_refeicoes);
        dest.writeInt(refeicoes_dia);
        dest.writeFloat(tot_custo);
    }

    public Refeicoes(Parcel in) {
       readFromParcel(in);
    }
    private void readFromParcel(Parcel in){
        id = in.readInt();
       //viagem = in.readParcelable(Viagem.class.getClassLoader());
        custo_refeicoes = in.readFloat();
        refeicoes_dia = in.readInt();
        tot_custo = in.readFloat();
    }


    public static float calculaValorFinal(Refeicoes rf){
        return ((rf.getRefeicoes_dia()*rf.getViagem().getTot_viajantes())*rf.getCusto_refeicoes())*rf.getViagem().getDuracao();
    }
}
