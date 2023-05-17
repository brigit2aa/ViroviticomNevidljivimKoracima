package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterOpisPraga extends BaseAdapter {

    Context context;
    ArrayList<OpisPraga> opisPragaArrayList;

    public AdapterOpisPraga(Context context, ArrayList<OpisPraga> opisPragaArrayList) {
        this.context = context;
        this.opisPragaArrayList = opisPragaArrayList;
    }

    @Override
    public int getCount() {
        return opisPragaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return opisPragaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.prag, null, false);
            TextView opisPraga = convertView.findViewById(R.id.opisPraga);
            opisPraga.setText(opisPragaArrayList.get(position).getOpisPraga());
        }
        return convertView;
    }
}
