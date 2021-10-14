package com.example.gerenciaviagens.bean;



import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.gerenciaviagens.EntretenimentoActivity;
import com.example.gerenciaviagens.database.dao.EntretenimentoDAO;
import com.example.gerenciaviagens.database.dao.GasolinaDAO;
import com.example.gerenciaviagens.database.dao.HospedagemDAO;
import com.example.gerenciaviagens.database.dao.RefeicoesDAO;
import com.example.gerenciaviagens.database.dao.Tarifa_aereaDAO;
import com.example.gerenciaviagens.database.dao.ViagemDAO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

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
        DecimalFormat df = new DecimalFormat("0.00");
        return titulo+"\nR$ "+df.format(custo_total);
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

    public static void insereViagem(final Viagem v, final Gasolina gas, final Tarifa_aerea ta, final Hospedagem hs, final Refeicoes rf, final List<Entretenimento> ent, final Context ctx){
        ViagemDAO vDAO = new ViagemDAO(ctx);
        GasolinaDAO gDAO = new GasolinaDAO(ctx);
        Tarifa_aereaDAO taDAO = new Tarifa_aereaDAO(ctx);
        HospedagemDAO hsDAO = new HospedagemDAO(ctx);
        RefeicoesDAO rfDAO = new RefeicoesDAO(ctx);
        EntretenimentoDAO eDAO = new EntretenimentoDAO(ctx);
        v.setCusto_total(Viagem.calculaTotCusto(v, gas, ta, hs, rf, ent));

        v.setCusto_pessoa(v.getCusto_total()/v.getTot_viajantes());
        long idViagem = vDAO.Insert(v);
        v.setId(idViagem);
        if (v.isGasolina()){
            gas.setViagem(v);
            gDAO.Insert(gas);
        }
        if (v.isTarifa_aerea()){
            ta.setViagem(v);
            taDAO.Insert(ta);
        }
        if (v.isHospedagem()){
            hs.setViagem(v);
            hsDAO.Insert(hs);
        }
        if (v.isRefeicoes()){
            rf.setViagem(v);
            rfDAO.Insert(rf);
        }
        if (v.isEntretenimento()){
            for (Entretenimento e: ent){
                e.setViagem(v);
                eDAO.Insert(e);
            }
        }
    }
    public static final float calculaTotCusto(final Viagem vi, final Gasolina gas, final Tarifa_aerea ta, final Hospedagem hs, final Refeicoes rf, final List<Entretenimento> ent){
        float totCusto = 0;
        if (vi.isGasolina()){
            totCusto+=gas.getTot_custo();
        }
        if(vi.isTarifa_aerea()){
            totCusto+=ta.getTot_custo();
        }
        if(vi.isHospedagem()){
            totCusto+= hs.getTot_custo();
        }
        if(vi.isRefeicoes()){
            totCusto+= rf.getTot_custo();
        }
        if(vi.isEntretenimento()){
            for(Entretenimento e : ent){
                totCusto += e.getValor();
            }
        }
        return totCusto;
    }
}
