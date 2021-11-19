package com.appetiser.coding_app_challenge_jamescalma.`interface`

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface HasDisposable {
    var disposables: CompositeDisposable
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
    fun dispose() {
        disposables.dispose()
    }
    fun handleError(throwable: Throwable) {
        Log.d("ErrorMessage","${throwable.message}")
    }
}