package com.javiersc.githubdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.javiersc.githubdemo.database.entity.User
import com.javiersc.githubdemo.databinding.ItemUserBinding

class UserAdapter : androidx.recyclerview.widget.ListAdapter<User, UserAdapter.UserViewHolder>(Diff()) {

    var onClick: ((user: User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(getItem(position))


    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            binding.user = user
            root.setOnClickListener { onClick?.invoke(user) }
            executePendingBindings()
        }

    }

    class Diff : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(old: User, new: User): Boolean = old.id == new.id
        override fun areContentsTheSame(old: User, new: User): Boolean = old == new

    }

}