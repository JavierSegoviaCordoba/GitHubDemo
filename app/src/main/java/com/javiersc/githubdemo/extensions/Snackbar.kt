package com.javiersc.githubdemo.extensions

import com.google.android.material.snackbar.Snackbar

inline fun Snackbar.onDismissed(crossinline onDismissed: () -> Unit) {
    addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            onDismissed()
        }
    })
}

inline fun Snackbar.onShown(crossinline onShown: () -> Unit) {
    addCallback(object : Snackbar.Callback() {
        override fun onShown(sb: Snackbar?) {
            super.onShown(sb)
            onShown()
        }
    })
}