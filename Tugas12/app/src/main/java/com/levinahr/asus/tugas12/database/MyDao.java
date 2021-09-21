package com.levinahr.asus.tugas12.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.levinahr.asus.tugas12.model.MovieModel;

import java.util.List;

@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addMovie(MovieModel movie);

    @Query("select * from movies")
    public List<MovieModel> getMovieList();

    @Delete
    public void deleteMovie(MovieModel movie);

    @Update
    public void updateMovie(MovieModel movie);

    @Query("DELETE FROM movies")
    public void nukeTable();
}
