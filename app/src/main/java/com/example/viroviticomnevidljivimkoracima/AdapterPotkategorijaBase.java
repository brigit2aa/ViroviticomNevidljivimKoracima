package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPotkategorijaBase extends BaseAdapter {

    Context context;
    ArrayList<Potkategorija> potkategorijaArrayList2;
    LayoutInflater inflater;

    public AdapterPotkategorijaBase(Context context, ArrayList<Potkategorija> potkategorijaArrayList2) {
        this.context = context;
        this.potkategorijaArrayList2 = potkategorijaArrayList2;
        inflater = (LayoutInflater.from(context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return potkategorijaArrayList2.size();
    }

    @Override
    public Object getItem(int position) {
        return potkategorijaArrayList2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.potkategorija, null);
        TextView nazivPotkategorije = convertView.findViewById(R.id.nazivPotkategorije);

        nazivPotkategorije.setText(potkategorijaArrayList2.get(position).getNazivPotkategorije());

        return convertView;
    }
}
