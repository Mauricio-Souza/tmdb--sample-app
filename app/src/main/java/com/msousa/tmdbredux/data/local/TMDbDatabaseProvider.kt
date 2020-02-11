package com.msousa.tmdbredux.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.msousa.tmdbredux.data.local.dao.IMovieDetailsDao
import com.msousa.tmdbredux.data.local.dao.IMoviesDao
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

@Database(entities = [MoviesEntity::class, MovieDetailsEntity::class], version = 1, exportSchema = false)
abstract class TMDbDatabaseProvider : RoomDatabase() {

    abstract fun moviesDao(): IMoviesDao
    abstract fun moviesDetailsDao(): IMovieDetailsDao

    companion object {
        private val DATABASE_NAME = "tmdb_database"
        @Volatile private var instance: TMDbDatabaseProvider? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: buildInstance(context).also { instance = it }
        }

        private fun buildInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TMDbDatabaseProvider::class.java,
                DATABASE_NAME
            ).build()
    }
}