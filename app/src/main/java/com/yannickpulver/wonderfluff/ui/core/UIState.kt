package com.yannickpulver.wonderfluff.ui.core

sealed class UIState<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : UIState<T>(data)
    class Loading<T>(data: T? = null) : UIState<T>(data)
    class Error<T>(exception: Exception, data: T? = null) : UIState<T>(data, exception)
}