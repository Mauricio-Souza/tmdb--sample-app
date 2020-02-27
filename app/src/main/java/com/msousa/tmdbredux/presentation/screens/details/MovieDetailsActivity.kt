package com.msousa.tmdbredux.presentation.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import com.msousa.tmdbredux.*
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoSuchDataFound
import com.msousa.tmdbredux.extensions.getColorCompat
import com.msousa.tmdbredux.extensions.getResourceValue
import com.msousa.tmdbredux.extensions.loadImageUrl
import com.msousa.tmdbredux.extensions.render
import com.msousa.tmdbredux.presentation.observer.LoadingObserver
import com.msousa.tmdbredux.presentation.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.vo.MovieDetailsVO
import com.msousa.tmdbredux.presentation.screens.base.BaseActivity
import com.msousa.tmdbredux.redux.actions.ViewAction
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_movie_details)

        intent.extras?.run {
            store.dispatcher(ViewAction.OnMovieDetailsActivityCreated(getString(MOVIE_ID).orEmpty()))
        } ?: error(getString(StringResource.MISSING_ARGUMENT_REQUIRED_ERROR))

        store.stateLiveData.observe(this, movieDetailsObserver)

        store.stateLiveData.observe(this, loadingObserverBehavior)

        store.stateLiveData.observe(this, errorObserver)

        store.stateLiveData.observe(this, activityNavigationObserver)

        store.stateLiveData.observe(this, fragmentNavigationObserver)
    }

    private val loadingObserverBehavior = LoadingObserver { visibility ->
        progressBar?.visibility = visibility
    }

    private val errorObserver = StateObserver<TMDbNoSuchDataFound> {
        store.dispatcher(ViewAction.OnNoSuchDataFound)
    }

    private val activityNavigationObserver = StateObserver<Intent> { intent ->
        startActivity(intent)
    }

    private val fragmentNavigationObserver = StateObserver<Fragment> { fragment ->
        fragment?.run { replaceFragment(this, ResourceId.container) }
    }

    private val movieDetailsObserver = StateObserver<MovieDetailsVO?> { movie ->
        movie?.render {
                scrollView?.visibility = View.VISIBLE
                showActionBarWithTitle(originalTitle)
                showActionBarWithBackButton()
                ivPoster?.loadImageUrl(posterPath, radius)
                tvRuntime?.text = runtime
                tvReleaseDate?.text = releaseDate
                tvRate?.text = setVoteAverage(voteAverage)
                tvSynopsis?.text = overview
                tvVotes?.text = voteCount
                tvRevenue?.text = revenue
        }
    }

    private fun setVoteAverage(average: String) : Spannable {
        val start = 3
        val end = 6
        val textSize = getResourceValue(DimenResource.vote_average_text_size)
        return SpannableStringBuilder().apply {
            append("$average/10")
            setSpan(
                ForegroundColorSpan(getColorCompat(ColorResource.mediumGray)),
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