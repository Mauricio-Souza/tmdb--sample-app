package com.msousa.tmdbredux.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.ResourceId
import com.msousa.tmdbredux.StringResource
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.viewObjects.MovieDetailsVO
import com.msousa.tmdbredux.redux.actions.ViewAction.*
import kotlinx.android.synthetic.main.activity_home.*

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
        } ?: error(getString(StringResource.MISSING_ARGUMENT_REQUIRED_ERROR))

        store.stateLiveData.observe(this, movieDetailsObserver)

        store.stateLiveData.observe(this, loadingObserverWithBehavior(ResourceId.progressBar))
    }

    private val movieDetailsObserver = StateObserver<MovieDetailsVO> { movie ->
        movie?.run {
            showActionBarWithTitle(originalTitle)
            home?.text = toString()
        }
    }
}