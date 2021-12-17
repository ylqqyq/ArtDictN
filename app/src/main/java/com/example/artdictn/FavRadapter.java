package com.example.artdictn;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavRadapter extends RecyclerView.Adapter<FavRadapter.viewHolder> {

   ArrayList<Artwork> listOfArt;
    Context fav_context;

    interface artClickListener {
        void artSelected(Artwork selectedArt);
    }

    public ResultAdapter.artClickListener listener;

    public FavRadapter(ArrayList<Artwork> listOfArt, Context list_context) {
        this.listOfArt = listOfArt;
        this.fav_context = list_context;
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public ImageView getImg() {
            return imgView;
        }

        public TextView getTitle() {
            return titleView;
        }

        private final ImageView imgView;
        private final TextView titleView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.fav_img);
            titleView = itemView.findViewById(R.id.fav_title);


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.delete,0,"REMOVE");
        }
    }

    @NonNull
    @Override
    public FavRadapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(fav_context).inflate(R.layout.fav_cell,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.getTitle().setText(listOfArt.get(position).title);
        Glide.with(fav_context)
                .load(listOfArt.get(position).image_id)
                .centerCrop()
                .into(holder.getImg());

        //trigger context menu by long press
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.getAdapterPosition();
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.artSelected(listOfArt.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return listOfArt.size();
    }



}


