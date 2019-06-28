package com.javiersc.githubdemo.ui.main

import androidx.lifecycle.*
import com.javiersc.githubdemo.R
import com.javiersc.githubdemo.database.entity.User
import com.javiersc.githubdemo.repo.GitHubRepo
import com.javiersc.githubdemo.repo.RepoStatus
import com.javiersc.githubdemo.ui.ScreenState
import kotlinx.coroutines.launch

class MainViewModel(private val githubRepo: GitHubRepo) : ViewModel() {

    private val _screenStates = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = Transformations.switchMap(githubRepo.repoStatus) { repoStatus ->
        when (repoStatus) {
            is RepoStatus.SUCCESS -> _screenStates.value = ScreenState.SUCCESS
            is RepoStatus.ERROR.API -> _screenStates.value = ScreenState.ERROR(R.string.error_message)
            is RepoStatus.ERROR.DB -> _screenStates.value = ScreenState.ERROR(R.string.error_message)
            is RepoStatus.LOADING -> _screenStates.value = ScreenState.LOADING
        }
        return@switchMap _screenStates
    }

    val users: LiveData<List<User>> = githubRepo.getUsers()

    fun getUsers() = viewModelScope.launch { githubRepo.fetchUsers() }

    fun finishState() {
        _screenStates.value = ScreenState.FINISHED
    }

    init {
        getUsers()
    }

}