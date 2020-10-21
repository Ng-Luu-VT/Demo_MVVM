package com.example.demo_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demo_mvvm.databinding.FragmentRepoListBinding
import org.jetbrains.anko.longToast

class RepoListFragment : Fragment() {
    private lateinit var  viewDataBinding: FragmentRepoListBinding
    private lateinit var adapter: RepoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRepoListBinding.inflate(inflater,container,false).apply {
            viewModel = ViewModelProviders.of(this@RepoListFragment).get(RepoListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchRepoList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.repoListLive.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val  viewModel = viewDataBinding.viewmodel
        if (viewModel == null){
            adapter = RepoListAdapter(viewDataBinding.viewmodel!!)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepoListFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}