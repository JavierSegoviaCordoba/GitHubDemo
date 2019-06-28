package com.javiersc.githubdemo.ui

sealed class ScreenState {
    class ERROR(val errorStringId: Int) : ScreenState()
    object LOADING : ScreenState()
    object FINISHED : ScreenState()
    object SUCCESS : ScreenState()
}