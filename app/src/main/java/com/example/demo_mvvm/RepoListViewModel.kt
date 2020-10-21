package com.example.demo_mvvm

import com.example.demo_mvvm.model.RepoRepository
import com.example.demo_mvvm.view.BaseViewModel


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