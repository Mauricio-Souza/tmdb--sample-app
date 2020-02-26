package com.msousa.tmdbredux.presentation.screens.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.ResourceId
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoSuchDataFound
import com.msousa.tmdbredux.presentation.observer.LoadingObserver
import com.msousa.tmdbredux.presentation.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.vo.MoviesVO
import com.msousa.tmdbredux.presentation.screens.base.BaseActivity
import com.msousa.tmdbredux.redux.actions.ViewAction
import com.msousa.tmdbredux.redux.actions.ViewAction.OnMainActivityCreated
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_main)
        initRecyclerView()

        store.dispatcher(OnMainActivityCreated)

        store.stateLiveData.observe(this, loadingObserverBehavior)

        store.stateLiveData.observe(this, moviesObserver)

        store.stateLiveData.observe(this, activityNavigationObserver)

        store.stateLiveData.observe(this, fragmentNavigateObserver)

        store.stateLiveData.observe(this, errorObserver)
    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter { id ->
            store.dispatcher(ViewAction.OnListItemClicked(this, id))
        }

        recyclerMovies?.layoutManager = GridLayoutManager(this, 2, VERTICAL, false)
        recyclerMovies?.adapter = moviesAdapter
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

    private val activityNavigationObserver = StateObserver<Intent> { intent ->
        startActivity(intent)
    }

    private val fragmentNavigateObserver = StateObserver<Fragment> { fragment ->
        fragment?.let { replaceFragment(it, ResourceId.container) }
    }

    private val errorObserver = StateObserver<TMDbNoSuchDataFound> {
        store.dispatcher(ViewAction.OnNoInternetConnection)
    }
}