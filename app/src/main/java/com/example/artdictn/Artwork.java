package com.example.artdictn;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Artwork  {

    @PrimaryKey(autoGenerate = true)
    public int local_id;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "artist")
    String artist_display;
    String id;
    String image_id;
    int source;

    @Ignore
    String medium_display;
    String dimensions;
    String description;

    public Artwork() { }

    public Artwork(String id, String title,String image_id,int source) {
        this.id = id;
        this.title = title;
        this.image_id = image_id;
        this.source = source;
    }

    //constructor for list display
    public Artwork(String id, String title,int source) {
        this.id = id;
        this.title = title;
        this.source = source;

    }

    public Artwork(String image_id) {
        this.image_id = image_id;
    }

    public Artwork(String id, String title, String artist_display, String medium_display, String dimensions, String image_id, String description,int source) {
        this.id = id;
        this.title = title;
        this.artist_display = artist_display;
        this.medium_display = medium_display;
        this.dimensions = dimensions;
        this.image_id = image_id;
        this.description = description;
        this.source = source;
    }
}