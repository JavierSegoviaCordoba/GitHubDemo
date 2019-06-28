package com.javiersc.githubdemo.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.core.Try
import com.javiersc.githubapi.GitHubApi
import com.javiersc.githubapi.dto.UserDTO
import com.javiersc.githubdemo.database.GitHubDAO
import com.javiersc.githubdemo.database.entity.User
import com.javiersc.githubdemo.database.entity.UserDetail
import com.javiersc.githubdemo.database.entity.toUser
import com.javiersc.githubdemo.database.entity.toUserDetail


interface GitHubRepo {
    val repoStatus: LiveData<RepoStatus>
    suspend fun fetchUsers()
    suspend fun insertUsers(users: List<User>)
    fun getUsers(): LiveData<List<User>>
    suspend fun fetchUserDetail(username: String)
    suspend fun insertUserDetail(userDetail: UserDetail)
    fun getUserDetail(username: String): LiveData<UserDetail>
}

class GitHubRepoImpl(private val userApi: GitHubApi, private val dao: GitHubDAO) : GitHubRepo {

    private val _repoStatus = MutableLiveData<RepoStatus>()
    override val repoStatus: LiveData<RepoStatus> = _repoStatus

    override suspend fun fetchUsers() {
        _repoStatus.value = RepoStatus.LOADING

        when (val usersEither: Either<Throwable, List<UserDTO>> = Try { userApi.getUsers() }.toEither()) {
            is Either.Left -> {
                val throwable: Throwable = usersEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.API(it) }
            }
            is Either.Right -> {
                val userDtoList: List<UserDTO> = usersEither.b
                val users: List<User> = userDtoList.map { userDto -> userDto.toUser() }
                insertUsers(users)
            }
        }
    }

    override suspend fun insertUsers(users: List<User>) {
        when (val insertEither: Either<Throwable, Unit> = Try { dao.insertUsers(users) }.toEither()) {
            is Either.Left -> {
                val throwable: Throwable = insertEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.DB(it) }
            }
            is Either.Right -> {
                _repoStatus.value = RepoStatus.SUCCESS
            }
        }
    }

    override fun getUsers(): LiveData<List<User>> = dao.getUsers()

    override suspend fun fetchUserDetail(username: String) {
        _repoStatus.value = RepoStatus.LOADING

        when (val usersEither: Either<Throwable, UserDTO> = Try { userApi.getUser(username) }.toEither()) {
            is Either.Left -> {
                val throwable: Throwable = usersEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.API(it) }
            }
            is Either.Right -> {
                val userDto: UserDTO = usersEither.b
                println(userDto.toString())
                val userDetail: UserDetail = userDto.toUserDetail()
                insertUserDetail(userDetail)
            }
        }
    }

    override suspend fun insertUserDetail(userDetail: UserDetail) {
        when (val insertEither: Either<Throwable, Unit> = Try { dao.insertUserDetail(userDetail) }.toEither()) {
            is Either.Left -> {
                val throwable: Throwable = insertEither.a
                _repoStatus.value = throwable.message?.let { RepoStatus.ERROR.DB(it) }
            }
            is Either.Right -> {
                _repoStatus.value = RepoStatus.SUCCESS
            }
        }
    }

    override fun getUserDetail(username: String): LiveData<UserDetail> = dao.getUserDetail(username)

}