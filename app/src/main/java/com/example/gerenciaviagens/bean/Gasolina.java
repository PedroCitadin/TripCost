package com.example.gerenciaviagens.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Gasolina implements Parcelable {
    private long id;
    private Viagem viagem;
    private float tot_km;
    private float media_litro;
    private float custo_litro;
    private int tot_veiculo;
    private float tot_custo;

    public Gasolina() {
    }

    protected Gasolina(Parcel in) {
        id = in.readLong();
        viagem = in.readParcelable(Viagem.class.getClassLoader());
        tot_km = in.readFloat();
        media_litro = in.readFloat();
        custo_litro = in.readFloat();
        tot_veiculo = in.readInt();
        tot_custo = in.readFloat();
    }

    public static final Creator<Gasolina> CREATOR = new Creator<Gasolina>() {
        @Override
        public Gasolina createFromParcel(Parcel in) {
            return new Gasolina(in);
        }

        @Override
        public Gasolina[] newArray(int size) {
            return new Gasolina[size];
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

    public float getTot_km() {
        return tot_km;
    }

    public void setTot_km(float tot_km) {
        this.tot_km = tot_km;
    }

    public float getMedia_litro() {
        return media_litro;
    }

    public void setMedia_litro(float media_litro) {
        this.media_litro = media_litro;
    }

    public float getCusto_litro() {
        return custo_litro;
    }

    public void setCusto_litro(float custo_litro) {
        this.custo_litro = custo_litro;
    }

    public int getTot_veiculo() {
        return tot_veiculo;
    }

    public void setTot_veiculo(int tot_veiculo) {
        this.tot_veiculo = tot_veiculo;
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
        dest.writeFloat(tot_km);
        dest.writeFloat(media_litro);
        dest.writeFloat(custo_litro);
        dest.writeInt(tot_veiculo);
        dest.writeFloat(tot_custo);
    }

    public static float calculaValorFinal(Gasolina gas){
        return ((gas.getTot_km()/gas.getMedia_litro())*gas.getCusto_litro())*gas.getTot_veiculo();
    }
}
