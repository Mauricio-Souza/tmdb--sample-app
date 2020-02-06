package com.msousa.tmdbredux.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.presentation.models.observer.LoadingObserver
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO
import com.msousa.tmdbredux.presentation.models.viewObjects.MoviesVO
import com.msousa.tmdbredux.redux.actions.ViewAction.OnListItemClicked
import com.msousa.tmdbredux.redux.actions.ViewAction.OnMainActivityCreated
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_main)
        initRecyclerView()

        store.dispatcher(OnMainActivityCreated)

        store.stateLiveData.observe(this, loadingObserverBehavior)

        store.stateLiveData.observe(this, moviesObserver)

        store.stateLiveData.observe(this, navigationObserver)

        store.stateLiveData.observe(this, errorObserver)

    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter { id, view ->
            imageView = view
            store.dispatcher(OnListItemClicked(this, id))
        }
        recyclerMovies.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerMovies.adapter = moviesAdapter
    }

    private val loadingObserverBehavior = LoadingObserver { visibility ->
        progressBar?.visibility = visibility
    }

    private val moviesObserver = StateObserver<MoviesVO> { movies ->
        movies?.run {
            showActionBarWithTitle(name)
            moviesAdapter.submitList(items)
        }
    }

    private val navigationObserver = StateObserver<Intent> { intent ->
        val imgViewPair = Pair.create<View, String>(
            imageView,
            ViewCompat.getTransitionName(imageView)
        )
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, imgViewPair)
        startActivity(intent, options.toBundle())
    }

    private val errorObserver = StateObserver<ErrorMessageVO> { error ->
        showShortToast(error?.message)
    }
}