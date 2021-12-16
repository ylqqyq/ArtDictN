package com.example.artdictn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.xml.transform.Source;

public class FavAdapter extends BaseAdapter {

    ArrayList<Artwork> listOfArt;
    Context list_context;
    public ImageView img;

    public FavAdapter(ArrayList<Artwork> listOfArt, Context list_context) {
        this.listOfArt = listOfArt;
        this.list_context = list_context;
    }

    @Override
    public int getCount() {
        return listOfArt.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(list_context).inflate(R.layout.list_cell,null);
        TextView title = convertView.findViewById(R.id.item_title);
        TextView source_txt= convertView.findViewById(R.id.item_source);
        img = convertView.findViewById(R.id.image_cover);
        title.setText(listOfArt.get(position).title);
        int sourceID = listOfArt.get(position).source;
        if (sourceID == 1) {
            source_txt.setText("Art Institute of Chicago");
        } else if (sourceID == 2) {
            source_txt.setText("Rijksmuseum");
        } else if (sourceID == 3) {
            source_txt.setText("Metropolitan Museum");
        }

        //make api call with id for image




        return convertView;
    }
}
