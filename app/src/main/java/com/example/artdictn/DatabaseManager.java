package com.example.artdictn;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DatabaseManager {

    static FavDatabase db;
    ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseAllFavArtList(List<Artwork> list);
    }

    public DatabaseListener listener;

    private static void BuildDBInstance(Context c) {
        db = Room.databaseBuilder(c, FavDatabase.class, "art_db").build();
    }

    public static FavDatabase getDBInstance(Context c) {
        if (db == null) {
            BuildDBInstance(c);
        }
        return db;
    }

    public void addNewArt(Artwork art) {
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().insertNewArt(art);
            }
        });
    }

    public void deleteArt(Artwork art) {
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().deleteArt(art);
            }
        });
    }

    public void deleteAll() {
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().delete();
            }
        });
    }

    public void getAllArt(){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                List<Artwork> list = db.getDao().getAll();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllFavArtList(list);
                    }
                });
            }
        });
    }


}
