package com.javiersc.githubdemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.javiersc.githubdemo.databinding.ActivityMainBinding
import com.javiersc.githubdemo.extensions.snackbarOnShown
import com.javiersc.githubdemo.ui.ScreenState
import com.javiersc.githubdemo.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupLoadAndRefresh()
        setupScreenState()

    }

    private fun setupLoadAndRefresh() {
        binding.adapter = userAdapter
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.getUsers() }

        viewModel.users.observe(this@MainActivity, Observer { users ->
            userAdapter.apply {
                submitList(users)
                onClick = { user -> DetailActivity.navigate(this@MainActivity, user.login) }
            }
        })
    }

    private fun setupScreenState() {
        viewModel.screenState.observe(this@MainActivity, Observer { screenState ->
            when (screenState) {
                is ScreenState.LOADING -> binding.swipeRefreshLayout.isRefreshing = true
                is ScreenState.ERROR -> binding.root.snackbarOnShown(message = getString(screenState.errorStringId)) {
                    viewModel.finishState()
                }
                is ScreenState.FINISHED -> binding.swipeRefreshLayout.isRefreshing = false
                is ScreenState.SUCCESS -> binding.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

}
