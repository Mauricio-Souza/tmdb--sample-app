package com.msousa.tmdbredux.data.local

import com.msousa.tmdbredux.data.local.dao.IMovieDetailsDao
import com.msousa.tmdbredux.data.local.dao.IMoviesDao
import com.msousa.tmdbredux.data.local.entities.IBaseMovieEntity
import com.msousa.tmdbredux.data.local.entities.IMovieEntity
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

class TMDbDatabaseDataSource(
    private val moviesDao: IMoviesDao,
    private val movieDetailsDao: IMovieDetailsDao
) : ITMDbDatabaseDataSource {

    override fun insertMovies(vararg movies: MoviesEntity) {
        moviesDao.insertAll(movies = *movies)
    }

    override fun insertMovieDetails(movieDetails: MovieDetailsEntity) {
        movieDetailsDao.insert(movieDetails = movieDetails)
    }

    override fun selectAllMovies() = moviesDao.readAllMovies()

    override fun selectMovieDetails(movieId: Long) = movieDetailsDao.readMovieDetailsById(id = movieId)

}