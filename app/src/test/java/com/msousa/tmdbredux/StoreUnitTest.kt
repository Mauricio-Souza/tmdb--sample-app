package com.msousa.tmdbredux

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.redux.actions.ServerResponse
import com.msousa.tmdbredux.redux.actions.ViewAction
import com.msousa.tmdbredux.redux.state.State
import com.msousa.tmdbredux.redux.store.IStore
import com.msousa.tmdbredux.redux.store.Store
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class StoreUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var stateObserver: StateObserver<Any>
    private lateinit var store: IStore

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        stateObserver = mock()
        store = Store(mock())
        store.stateLiveData.observeForever(stateObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should change app state to loading status`() {
        store.dispatcher(ViewAction.OnMainActivityCreated)
        verify(stateObserver, times(1)).onChanged(State(data = ServerResponse.Loading))
        assertEquals(store.stateLiveData.value, State(data = ServerResponse.Loading))
    }
}