package com.example.demo_mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.demo_mvvm.model.repository.RepoRepository
import com.example.demo_mvvm.base.BaseViewModel
import com.example.demo_mvvm.model.api.Item


class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess)
            {
                repoListLive.value = response?.items
                empty.value = false
            }
            else {
                empty.value = true
            }
        }
    }
}