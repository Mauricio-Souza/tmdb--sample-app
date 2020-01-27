package com.msousa.tmdbredux.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import com.msousa.tmdbredux.*
import com.msousa.tmdbredux.extensions.getColorCompat
import com.msousa.tmdbredux.extensions.getDimens
import com.msousa.tmdbredux.extensions.getResourceValue
import com.msousa.tmdbredux.extensions.loadImageUrlWithCornerRadius
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.viewObjects.MovieDetailsVO
import com.msousa.tmdbredux.redux.actions.ViewAction.OnMovieDetailsActivityCreated
import com.nhaarman.mockitokotlin2.inOrder
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_movie_details)

        intent.extras?.run {
            store.dispatcher(OnMovieDetailsActivityCreated(getString(MOVIE_ID).orEmpty()))
        } ?: error(getString(StringResource.MISSING_ARGUMENT_REQUIRED_ERROR))

        store.stateLiveData.observe(this, movieDetailsObserver)

        store.stateLiveData.observe(this, loadingObserverBehavior(ResourceId.progressBar))
    }

    private val movieDetailsObserver = StateObserver<MovieDetailsVO> { movie ->
        movie?.run {
            scrollView?.visibility = View.VISIBLE
            runtimeAndReleaseDateGroup?.visibility = View.VISIBLE
            showActionBarWithTitle(originalTitle)
            showActionBarWithBackButton()
            ivPoster?.loadImageUrlWithCornerRadius(posterPath, radius)
            tvRuntime?.text = runtime
            tvReleaseDate?.text = releaseDate
            tvRate?.text = setVoteAverage(voteAverage)
            tvSynopsis?.text = overview
        }
    }

    private fun setVoteAverage(average: String) : Spannable {
        val start = 3
        val end = 6
        val textSize = getResourceValue(DimenResource.vote_average_text_size)
        return SpannableStringBuilder().apply {
            append("$average/10")
            setSpan(
                ForegroundColorSpan(getColorCompat(ColorResource.lightGray)),
                start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            setSpan(RelativeSizeSpan(textSize), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }.toSpannable()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFinishAfterTransition()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val MOVIE_ID = "movie_id"
        fun getIntent(context: Context, movieId: String) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }
}