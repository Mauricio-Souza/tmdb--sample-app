package com.msousa.tmdbredux.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity

@Dao
interface IMovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetailsEntity: MovieDetailsEntity)

    @Query("select * from movie_details where id = :id")
    suspend fun readMovieDetailsById(id: Long) : MovieDetailsEntity
}