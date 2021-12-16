package com.example.artdictn;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Artwork.class})
public abstract class FavDatabase extends RoomDatabase {
    abstract public DAO getDao();
}
