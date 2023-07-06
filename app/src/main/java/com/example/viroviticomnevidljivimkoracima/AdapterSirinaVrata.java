package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSirinaVrata extends BaseAdapter {

    Context context;
    ArrayList<SirinaVrata> sirinaVrataArrayList;

    public AdapterSirinaVrata(Context context, ArrayList<SirinaVrata> sirinaVrataArrayList) {
        this.context = context;
        this.sirinaVrataArrayList = sirinaVrataArrayList;
    }

    @Override
    public int getCount() {
        return sirinaVrataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sirinaVrataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sirina, null, false);
            TextView sirinaVrata = convertView.findViewById(R.id.sirinaVrata);

            sirinaVrata.setText(sirinaVrataArrayList.get(position).sirinaVrataCm);
        }
        return convertView;
    }
}
