package com.example.gerenciaviagens.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Hospedagem implements Parcelable {
    private long id;
    private Viagem viagem;
    private float custo_medio;
    private int tot_noites;
    private int tot_quartos;
    private float tot_custo;

    protected Hospedagem(Parcel in) {
        id = in.readLong();
        viagem = in.readParcelable(Viagem.class.getClassLoader());
        custo_medio = in.readFloat();
        tot_noites = in.readInt();
        tot_quartos = in.readInt();
        tot_custo = in.readFloat();
    }

    public static final Creator<Hospedagem> CREATOR = new Creator<Hospedagem>() {
        @Override
        public Hospedagem createFromParcel(Parcel in) {
            return new Hospedagem(in);
        }

        @Override
        public Hospedagem[] newArray(int size) {
            return new Hospedagem[size];
        }
    };

    public Hospedagem() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(viagem, flags);
        dest.writeFloat(custo_medio);
        dest.writeInt(tot_noites);
        dest.writeInt(tot_quartos);
        dest.writeFloat(tot_custo);
    }

    public static float calculaValorFinal(Hospedagem hs){
        return (hs.getCusto_medio()*hs.getTot_noites())*hs.getTot_quartos();
    }
}
