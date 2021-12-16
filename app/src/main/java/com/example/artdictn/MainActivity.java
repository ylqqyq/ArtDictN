package com.example.artdictn;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ResultAdapter.artClickListener,
        NetworkingService.networkingListener {
    ResultAdapter adapter;
    NetworkingService networkingService;
    //    ArrayList<Artwork> artListC;
    ArrayList<Artwork> artListR = new ArrayList<>(0);
    //    ArrayList<Artwork> fullList;
    JsonService jsonService;
    RecyclerView recyclerView;
    FavDatabase db;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        db = DatabaseManager.getDBInstance(this);
        dbManager = ((myApp)getApplication()).getDatabaseManager();

        //combine two arraylist for display
//        fullList.addAll(artListC);
//        fullList.addAll(artListR);

        recyclerView = findViewById(R.id.resultGrid);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultAdapter(this,artListR);
        recyclerView.setAdapter(adapter);
        adapter.listener=this;
        networkingService.listener = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        String keyword = searchView.getQuery().toString();
        if(!keyword.isEmpty()){
            searchView.setQuery(keyword,false);
            searchView.setIconified(false);}
        searchView.setQueryHint("Search for specific subjects");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                networkingService.fetchArtListData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.fav:{
                Intent toFav = new Intent(getApplicationContext(),FavActivity.class);
                startActivity(toFav);
                break;
                }
        }
        return true;
    }

    @Override
    public void artSelected(Artwork selectedArt) {
//        selectedArt =new Artwork();
        Log.d("select","ArtSelected!#@######");
        Intent toDetail = new Intent(this,DetailActivity.class);
        toDetail.putExtra("selectID",selectedArt.id);
        startActivity(toDetail);
        //should I put the object in or just id???
    }

    @Override
    public void APIlistener(String jsonString) {
//            artListC = jsonService.parseArtListJsonChi(jsonString);
        artListR = jsonService.parseArtListJsonRijks(jsonString);
        for (int i = 0; i < artListR.size(); i++) {
            networkingService.getImageDatafromRijks(artListR.get(i).image_id);
        }
        System.out.println("Size:"+artListR.size());
        adapter.artList = artListR;
        adapter.notifyDataSetChanged();
        //BIG PROBLEM: use this list of id to make a image id call?????
//        for(int i = 0; i < artListC.size(); i++) {
//        networkingService.getImgID(artListC.get(i).id);
//        }
//
//        adapter.artList = fullList;

    }

    @Override
    public void APIImgListener(Bitmap image) {
    adapter.
    }
}