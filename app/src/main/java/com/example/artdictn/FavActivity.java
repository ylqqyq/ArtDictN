package com.example.artdictn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements
        DatabaseManager.DatabaseListener,NetworkingService.networkingListener,FavRadapter.artClickListener{

    ArrayList<Artwork> favDBList;
    DatabaseManager dbManager;
//    FragmentManager fm = new

    RecyclerView recyclerView;
    FavRadapter rAdapter;
    NetworkingService networkingService;
    JsonService jsonService;
    AlertDialog.Builder warning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        recyclerView = findViewById(R.id.fav_view);
        registerForContextMenu(recyclerView);
        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService=((myApp)getApplication()).getJsonService();


        dbManager =((myApp)getApplication()).getDatabaseManager();
        dbManager.listener = this;
        dbManager.getAllArt();



    }

    @Override
    public void databaseAllFavArtList(List<Artwork> list) {
        favDBList = new ArrayList<>(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rAdapter = new FavRadapter(favDBList,this);
        recyclerView.setAdapter(rAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.remove_all:
                showAlert();

                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete:
//                dbManager.deleteArt(favDBList.get(info.position));
                dbManager.deleteArt(favDBList.get(info.position));
                rAdapter.notifyDataSetChanged();
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void APIlistener(String jsonString) {

    }

    @Override
    public void APIImgListener(Bitmap image) {


    }

    @Override
    public void artSelected(Artwork selectedArt) {
        Intent toFull = new Intent(this,FullimgActivity.class);
        toFull.putExtra("img_url",selectedArt.image_id);
        startActivity(toFull);

    }

    private void showAlert(){
        warning.create();
        warning.setMessage("You will delete all saved artwork.");
        warning.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbManager.deleteAll();

            }
        });
        warning.setCancelable(true);
    }
}