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
    ArrayList<Potkategorija> potkategorijaArrayList;

    public AdapterPotkategorijaBase(Context context, ArrayList<Potkategorija> potkategorijaArrayList) {
        this.context = context;
        this.potkategorijaArrayList = potkategorijaArrayList;
    }

    @Override
    public int getCount() {
        return potkategorijaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return potkategorijaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.potkategorija, null, false);
            TextView nazivPotkategorije = convertView.findViewById(R.id.nazivPotkategorije);

            nazivPotkategorije.setText(potkategorijaArrayList.get(position).getNazivPotkategorije());
        }
        return convertView;
    }
}
