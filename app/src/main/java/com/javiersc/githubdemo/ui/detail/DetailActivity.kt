package com.javiersc.githubdemo.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.javiersc.githubdemo.R
import com.javiersc.githubdemo.databinding.DetailActivityBinding
import com.javiersc.githubdemo.extensions.font
import com.javiersc.githubdemo.extensions.snackbarOnShown
import com.javiersc.githubdemo.ui.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailActivity : AppCompatActivity() {

    private val binding by lazy { DetailActivityBinding.inflate(layoutInflater) }
    private val username by lazy { intent.extras?.getString(KEY_USER) }
    private val viewModel by viewModel<DetailViewModel> { parametersOf(username) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupLoadAndRefresh()
        setupScreenState()
    }

    private fun setupLoadAndRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener { username?.let { username -> viewModel.fetchUserDetail(username) } }
        binding.collapsingToolbarLayout.apply {
            setExpandedTitleColor(ContextCompat.getColor(this@DetailActivity, android.R.color.white))
            setCollapsedTitleTextColor(ContextCompat.getColor(this@DetailActivity, android.R.color.white))
            setExpandedTitleTypeface(font(R.font.google_sans_medium))
            setCollapsedTitleTypeface(font(R.font.google_sans_medium))
        }

        viewModel.userDetail.observe(this, Observer { userDetail ->
            binding.userDetail = userDetail
        })
    }

    private fun setupScreenState() {
        viewModel.screenState.observe(this@DetailActivity, Observer { screenState ->
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


    companion object {
        private const val KEY_USER = "KEY_USER"
        fun navigate(context: Context, username: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_USER, username)
            context.startActivity(intent)
        }
    }

}
