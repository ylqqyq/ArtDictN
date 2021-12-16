package com.example.artdictn;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Artwork implements Parcelable {

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




    public Artwork() {

    }

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

    protected Artwork(Parcel in) {
        id = in.readString();
        title = in.readString();
        artist_display = in.readString();
        medium_display = in.readString();
        dimensions = in.readString();
        image_id = in.readString();
        description = in.readString();
        source = in.readInt();
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(artist_display);
        dest.writeString(medium_display);
        dest.writeString(dimensions);
        dest.writeString(image_id);
        dest.writeString(description);
        dest.writeInt(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Artwork> CREATOR = new Creator<Artwork>() {
        @Override
        public Artwork createFromParcel(Parcel in) {
            return new Artwork(in);
        }

        @Override
        public Artwork[] newArray(int size) {
            return new Artwork[size];
        }
    };
}