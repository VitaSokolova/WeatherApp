package ru.surfstudio.weatherapp.domain.common

sealed class LoadableData<T>
class Loading<T> : LoadableData<T>()
class Normal<T>(val data: T) : LoadableData<T>()
class Error<T>(val error: Throwable? = null) : LoadableData<T>()