package com.example.artdictn;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface DAO {
    @Query("SELECT * FROM Artwork")
    List<Artwork> getAll();

    @Insert
    void insertNewArt(Artwork artNew);

    @Delete
    void deleteArt(Artwork artDelete);

    @Query("DELETE FROM Artwork")
    public void delete();

//    @Query("DELETE FROM Artwork")
//    abstract void deleteArtQ(Artwork toDelete);
}
