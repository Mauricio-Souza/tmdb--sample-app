package com.msousa.tmdbredux.data.local

interface ITMDbDatabaseRepository {
    fun <T> insert(param: T)
    fun <T> update(param: T)
    fun <T> delete(param : T)
    fun <T> select(param: T)
}