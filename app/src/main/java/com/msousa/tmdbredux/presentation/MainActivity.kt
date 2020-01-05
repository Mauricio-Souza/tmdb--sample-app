package com.msousa.tmdbredux.presentation

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msousa.tmdbredux.LayoutResource
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

        store.stateLiveData.observe(this, StateObserver<MoviesVO> { movies ->
            titlePage?.text = movies?.name
            moviesAdapter.submitList(movies?.items)
        })

        store.stateLiveData.observe(this, StateObserver<ErrorMessageVO> { error ->
            showShortToast(error?.message)
        })

    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter { id ->
            startActivity(HomeActivity.getIntent(this, id))
        }
        recyclerMovies.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerMovies.adapter = moviesAdapter
    }
}