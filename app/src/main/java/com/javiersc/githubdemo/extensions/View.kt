package com.javiersc.githubdemo.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

inline fun View.snackbarOnDismissed(message: String, crossinline onDismissed: () -> Unit) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.onDismissed { onDismissed() }
    snackbar.show()
}

inline fun View.snackbarOnShown(message: String, crossinline onShown: () -> Unit) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.onShown { onShown() }
    snackbar.show()
}