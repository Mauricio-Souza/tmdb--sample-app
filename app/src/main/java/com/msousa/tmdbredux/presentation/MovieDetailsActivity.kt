package com.msousa.tmdbredux.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.R
import com.msousa.tmdbredux.redux.actions.ViewAction.*

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun getIntent(context: Context, movieId: String) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_home)

        intent.extras?.getString(MOVIE_ID)?.run {
            store.dispatcher(OnMovieDetailsActivityCreated(this))
        }

    }
}