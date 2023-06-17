package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class AdapterPrilagodbeBasic extends BaseAdapter {

    Context context;
    ArrayList<Prilagodba> prilagodbaArrayList;

    public AdapterPrilagodbeBasic(Context context, ArrayList<Prilagodba> prilagodbaArrayList) {
        this.context = context;
        this.prilagodbaArrayList = prilagodbaArrayList;
    }

    @Override
    public int getCount() {
        return prilagodbaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return prilagodbaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.prilagodba, null, false);
            ImageView opisPrilagodbe = convertView.findViewById(R.id.opisPrilagodbe);

            opisPrilagodbe.setImageResource(prilagodbaArrayList.get(position).getOpisPrilagodbe());
        }
        return convertView;
    }
}
