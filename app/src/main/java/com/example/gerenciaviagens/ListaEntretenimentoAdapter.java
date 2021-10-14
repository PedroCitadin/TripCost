package com.example.gerenciaviagens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gerenciaviagens.bean.Entretenimento;

import java.util.List;

public class ListaEntretenimentoAdapter extends BaseAdapter {
    private Context contexto;
    private List<Entretenimento> lista;

    public ListaEntretenimentoAdapter(Context contexto, List<Entretenimento> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(contexto, R.layout.item_entretenimento, null);
        TextView lblNomeEntretenimento = (TextView) v.findViewById(R.id.lblNomeEntretenimento);
        TextView lblValorEntretenimento = (TextView) v.findViewById(R.id.lblValorEntretenimento);
        lblNomeEntretenimento.setText(lista.get(position).getDescricao());
        lblValorEntretenimento.setText("R$ "+lista.get(position).getValor());
        v.setTag(lista.get(position).getId());
        return v;
    }
}
