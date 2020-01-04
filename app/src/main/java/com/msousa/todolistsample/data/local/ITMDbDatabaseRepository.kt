package com.msousa.todolistsample.data.local

interface ITMDbDatabaseRepository {

    fun <T> save(data: T)
    fun <T> update(data: T)
    fun <T> delete(data : T)
    fun <T> select(data: T)
}