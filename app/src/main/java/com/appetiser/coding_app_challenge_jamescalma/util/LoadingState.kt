package com.appetiser.coding_app_challenge_jamescalma.util

data class LoadingState(val status: Status, val message: String?) {

    companion object {

        fun success(msg: String): LoadingState {
            return LoadingState(Status.SUCCESS, msg)
        }

        fun error(msg: String): LoadingState {
            return LoadingState(Status.ERROR, msg)
        }

        fun loading(): LoadingState {
            return LoadingState(Status.LOADING, null)
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}