package com.msousa.tmdbredux.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msousa.tmdbredux.R
import com.msousa.tmdbredux.redux.actions.ViewAction.*

class HomeActivity : BaseActivity() {

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun getIntent(context: Context, movieId: String) =
            Intent(context, HomeActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        intent.extras?.run {
            store.dispatcher(OnMovieClicked(getString(MOVIE_ID).orEmpty()))
        }

    }
}