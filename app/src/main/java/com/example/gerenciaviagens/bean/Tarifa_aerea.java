package com.example.gerenciaviagens.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarifa_aerea implements Parcelable {
    private long id;
    private Viagem viagem;
    private float custo_pessoa;
    private float aluguel_veiculo;
    private float tot_custo;

    public Tarifa_aerea() {
    }

    protected Tarifa_aerea(Parcel in) {
        id = in.readLong();
       // viagem = in.readParcelable(Viagem.class.getClassLoader());
        custo_pessoa = in.readFloat();
        aluguel_veiculo = in.readFloat();
        tot_custo = in.readFloat();
    }

    public static final Creator<Tarifa_aerea> CREATOR = new Creator<Tarifa_aerea>() {
        @Override
        public Tarifa_aerea createFromParcel(Parcel in) {
            return new Tarifa_aerea(in);
        }

        @Override
        public Tarifa_aerea[] newArray(int size) {
            return new Tarifa_aerea[size];
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

    public float getCusto_pessoa() {
        return custo_pessoa;
    }

    public void setCusto_pessoa(float custo_pessoa) {
        this.custo_pessoa = custo_pessoa;
    }

    public float getAluguel_veiculo() {
        return aluguel_veiculo;
    }

    public void setAluguel_veiculo(float aluguel_veiculo) {
        this.aluguel_veiculo = aluguel_veiculo;
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
      //  dest.writeParcelable(viagem, flags);
        dest.writeFloat(custo_pessoa);
        dest.writeFloat(aluguel_veiculo);
        dest.writeFloat(tot_custo);
    }

    public static float calculaValorFinal(Tarifa_aerea ta){
        return (ta.getCusto_pessoa()*ta.getViagem().getTot_viajantes())+ta.getAluguel_veiculo();
    }
}
