package com.msousa.tmdbredux.data.local

import com.msousa.tmdbredux.data.local.dao.IMovieDetailsDao
import com.msousa.tmdbredux.data.local.dao.IMoviesDao
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

class TMDbDatabaseDataSource(
    private val moviesDao: IMoviesDao,
    private val movieDetailsDao: IMovieDetailsDao
) : ITMDbDatabaseDataSource {

    override suspend fun insertMovies(movies: MoviesEntity) {
        moviesDao.insertAll(movies = movies)
    }

    override suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity) {
        movieDetailsDao.insert(movieDetailsEntity = movieDetailsEntity)
    }

    override suspend fun selectAllMovies() = moviesDao.readAllMovies()

    override suspend fun selectMovieDetails(movieId: Long) =
        movieDetailsDao.readMovieDetailsById(id = movieId)

}