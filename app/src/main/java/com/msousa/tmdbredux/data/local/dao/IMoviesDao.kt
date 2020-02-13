package com.msousa.tmdbredux.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

@Dao
interface IMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: MoviesEntity)

    @Query("select * from movies")
    fun readAllMovies() : MoviesEntity
}