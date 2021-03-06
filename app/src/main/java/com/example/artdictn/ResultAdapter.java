package com.example.artdictn;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.myViewHolder>{

    Context c;
    public ArrayList<Artwork> artList;

    //Adapter for homeActivity's recyclerview
    public ResultAdapter(Context c, ArrayList<Artwork> artList) {
        this.c = c;
        this.artList = artList;
    }

    interface artClickListener {
        void artSelected(Artwork selectedArt);
    }

    public artClickListener listener;

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView txtView;
        private final ImageView imgView;
        public TextView getTxtView() {
            return txtView;
        }
        public ImageView getImgView() {
            return imgView;
        }

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.result_img);
            txtView = itemView.findViewById(R.id.result_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Artwork artwork = artList.get(getAdapterPosition());
            listener.artSelected(artwork);
        }
    }

    @NonNull
    @Override
    public ResultAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.result_cell,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.getTxtView().setText(artList.get(position).title);
        Glide.with(c)
                .load(artList.get(position).image_id)
                .centerCrop()
                .into(holder.getImgView());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.artSelected(artList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return artList.size();
    }
}
