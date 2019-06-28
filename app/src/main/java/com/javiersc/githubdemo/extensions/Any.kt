package com.javiersc.githubdemo.extensions

import android.util.Log

private const val FILTER = "DevFilter"

fun Any.logV(text: String) {
    Log.v("$FILTER: ${this::class.java.simpleName}", text)
}

fun Any.logE(text: String) {
    Log.e("$FILTER: ${this::class.java.simpleName}", text)
}