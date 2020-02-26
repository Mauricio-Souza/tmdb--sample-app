package com.msousa.tmdbredux.redux.state

data class State(val data: Any?)

inline fun <reified T> State.unwrap(consumer: (T) -> Unit) {
    if (data is T) consumer(data)
}

