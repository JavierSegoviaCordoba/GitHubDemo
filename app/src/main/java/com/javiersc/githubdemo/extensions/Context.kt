package com.javiersc.githubdemo.extensions

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat


fun Context.font(fontId: Int): Typeface? = try {
    ResourcesCompat.getFont(this, fontId)
} catch (e: Throwable) {
    Typeface.DEFAULT
}