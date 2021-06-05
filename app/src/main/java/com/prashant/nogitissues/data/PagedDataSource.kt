package com.prashant.nogitissues.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.prashant.nogitissues.network.IssueService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class PagedDataSource (private val issueService: IssueService) :
    PageKeyedDataSource<Long, IssueItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, IssueItem>
    ) {

        issueService.getIssues(1, 10)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                it?.let {
                    callback.onResult(it, null, 2)
                }
            }, {
                Log.v("PagingLogs", "message ${it.localizedMessage}")
            })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, IssueItem>) {

    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, IssueItem>) {

        issueService.getIssues((params.key).toInt(), 10)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                it?.let {
                    callback.onResult(it, params.key + 1)
                }
            }, {
                Log.v("PagingLogs", "message ${it.localizedMessage}")
            })

    }
}