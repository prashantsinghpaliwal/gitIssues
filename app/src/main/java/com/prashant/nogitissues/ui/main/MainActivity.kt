package com.prashant.nogitissues.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.prashant.nogitissues.R
import com.prashant.nogitissues.common.render
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),  PagedIssueAdapter.ItemClickListener {

//    IssuesAdapter.ItemClickListener,
    private val viewModel by viewModels<IssuesViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private lateinit var issuesAdapter: IssuesAdapter
    private lateinit var pagedAdapter: PagedIssueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpObservers() {

//        viewModel.getIssues(1, 10)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe ({
//                it.render(onLoading = {
//
//
//                },
//                    onSuccess = { data ->
//
//                        data?.let {
//                            issuesAdapter.set(it)
//                        }
//                    },
//                    onError = {
//                    })
//            },{
//                Log.v("LogTags","${it.localizedMessage}")
//            })

        viewModel.getPagedData()?.observe(this, { pagedList ->

            pagedList?.let {
                pagedAdapter.submitList(it)
            }

        })
    }

    private fun setUpUi() {
        with(binding) {

//            issuesAdapter = IssuesAdapter(this@MainActivity)
            pagedAdapter = PagedIssueAdapter(this@MainActivity)
            issueRecyclerView.apply {
                this.adapter = pagedAdapter
                setHasFixedSize(true)
            }

        }
    }

    override fun onItemClicked(item: IssueItem) {

    }
}