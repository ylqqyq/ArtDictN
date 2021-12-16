package com.example.artdictn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements DatabaseManager.DatabaseListener,NetworkingService.networkingListener{

    ArrayList<Artwork> favDBList;
    DatabaseManager dbManager;
    ListView listView;
    NetworkingService networkingService;
    JsonService jsonService;
    FavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService=((myApp)getApplication()).getJsonService();

        listView = findViewById(R.id.art_list);
        dbManager =((myApp)getApplication()).getDatabaseManager();
        dbManager.listener = this;
        dbManager.getAllArt();


    }

    @Override
    public void databaseAllFavArtList(List<Artwork> list) {
        favDBList = new ArrayList<>(list);
        adapter = new FavAdapter(favDBList,this);

        for (int i=0;i<favDBList.size();i++) {
            networkingService.getImageDatafromRijks(favDBList.get(i).image_id);
        }
        listView.setAdapter(adapter);

    }

    @Override
    public void APIlistener(String jsonString) {

    }

    @Override
    public void APIImgListener(Bitmap image) {
        adapter.img.setImageBitmap(image);

    }
}