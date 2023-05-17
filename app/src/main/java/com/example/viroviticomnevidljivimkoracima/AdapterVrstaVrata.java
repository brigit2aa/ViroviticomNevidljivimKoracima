package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterVrstaVrata extends BaseAdapter {

    Context context;
    ArrayList<VrstaVrata> vrataArrayList;

    public AdapterVrstaVrata(Context context, ArrayList<VrstaVrata> vrataArrayList) {
        this.context = context;
        this.vrataArrayList = vrataArrayList;
    }

    @Override
    public int getCount() {
        return vrataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return vrataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.vrata, null, false);
            TextView nazivVrata = convertView.findViewById(R.id.nazivVrata);

            nazivVrata.setText(vrataArrayList.get(position).getNazivVrata());

        }
        return convertView;
    }
}

