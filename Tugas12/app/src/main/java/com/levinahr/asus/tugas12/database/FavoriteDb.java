package com.levinahr.asus.tugas12.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.levinahr.asus.tugas12.model.Converters;
import com.levinahr.asus.tugas12.model.MovieModel;

@Database(entities = {MovieModel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class FavoriteDb extends RoomDatabase {
    public abstract MyDao myDao();
}
