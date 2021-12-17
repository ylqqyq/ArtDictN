package com.example.artdictn;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements NetworkingService.networkingListener{

    JsonService jsonService;
    NetworkingService networkingService;
    TextView title_text;
    TextView maker_text;
    TextView medium_text;
    TextView dimension_text;
    TextView desc_text;
    ImageView imageView;
    ImageButton save_btn;
    Artwork artDetailData = new Artwork();
    FragmentManager fm = getSupportFragmentManager();

    DatabaseManager dbManager;
//    FavDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        db = DatabaseManager.getDBInstance(this);
        dbManager = ((myApp)getApplication()).getDatabaseManager();
        String selectedID =this.getIntent().getStringExtra("selectID");


        Log.d("select","art of choice:" +artDetailData);
        title_text = findViewById(R.id.title);
        maker_text = findViewById(R.id.artist);
        medium_text = findViewById(R.id.medium);
        dimension_text = findViewById(R.id.dimension);
        desc_text = findViewById(R.id.desc);
        imageView = findViewById(R.id.myImage);
        save_btn = findViewById(R.id.save_btn);
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingService = ((myApp)getApplication()).getNetworkingService();
        networkingService.listener = this;
        networkingService.fetchDetailData(selectedID);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("detail","biggggggggpicture");
//                addFragment(artDetailData.image_id);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(artDetailData.image_id)));

                
            }
//
        });

    }

//    void addFragment(String img_url) {
//        fm.findFragmentById(R.id.full_image_frag);
//        LargeFragment frag = LargeFragment.newInstance(img_url);
//        fm.beginTransaction().add(R.id.full_image_frag,frag).commit();
//
//    }


    @Override
    public void APIlistener(String jsonString) {
        artDetailData = jsonService.parseArtDetailJsonRijks(jsonString);
        title_text.setText(artDetailData.title);
        maker_text.setText(artDetailData.artist_display);
        medium_text.setText("Medium: "+artDetailData.medium_display);
        dimension_text.setText("Dimensions: "+artDetailData.dimensions);
        if (artDetailData.description == null) {
            desc_text.setText("Description: Not available");
        } else {
        desc_text.setText("Description: "+artDetailData.description);
        }

        networkingService.getImageDatafromRijks(artDetailData.image_id);
    }

    @Override
    public void APIImgListener(Bitmap image) {
        imageView.setImageBitmap(image);
    }
//NOT YET THERE: a function to save the artwork into database

    public void save_item(View view) {

        Log.d("db","object addedddddddddddddddd");
        save_btn.setImageResource(R.drawable.outline_favorite_white_24dp);
        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
        dbManager.addNewArt(artDetailData);



    }


//        Intent toFull = new Intent(this,FullscreenActivity.class);
//        toFull.putExtra("image_url",artDetailData.image_id);
//        startActivity(toFull);


    }
