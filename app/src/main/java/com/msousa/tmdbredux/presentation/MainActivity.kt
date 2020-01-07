package com.msousa.tmdbredux.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msousa.tmdbredux.LayoutResource
import com.msousa.tmdbredux.ResourceId
import com.msousa.tmdbredux.presentation.models.observer.LoadingObserver
import com.msousa.tmdbredux.redux.actions.ViewAction.*
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO
import com.msousa.tmdbredux.presentation.models.viewObjects.MoviesVO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LayoutResource.activity_main)
        initRecyclerView()

        store.dispatcher(OnMainActivityCreated)

        store.stateLiveData.observe(this, loadingObserverWithBehavior(ResourceId.progressBar))

        store.stateLiveData.observe(this, moviesObserver)

        store.stateLiveData.observe(this, navigationObserver)

        store.stateLiveData.observe(this, errorObserver)

    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter { id ->
            store.dispatcher(OnListItemClicked(this, id))
        }
        recyclerMovies.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerMovies.adapter = moviesAdapter
    }

    private val moviesObserver = StateObserver<MoviesVO> { movies ->
        movies?.run {
            showActionBarWithTitle(name)
            moviesAdapter.submitList(items)
        }
    }

    private val navigationObserver = StateObserver<Intent> { intent ->
        startActivity(intent)
    }

    private val errorObserver = StateObserver<ErrorMessageVO> { error ->
        showShortToast(error?.message)
    }
}