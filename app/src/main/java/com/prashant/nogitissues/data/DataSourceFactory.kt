package com.prashant.nogitissues.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.prashant.nogitissues.network.IssueService


class DataSourceFactory (private val issueService: IssueService) :
    DataSource.Factory<Long, IssueItem>() {

    private var pagedDataSource: PagedDataSource? = null
    private var mutableLiveData = MutableLiveData<PagedDataSource>()

    init {
        mutableLiveData = MutableLiveData<PagedDataSource>()
    }

   override fun create(): DataSource<Long, IssueItem>? {
        pagedDataSource = PagedDataSource(issueService)
        mutableLiveData.postValue(pagedDataSource)
        return pagedDataSource
    }

    fun getMutableLiveData(): MutableLiveData<PagedDataSource> {
        return mutableLiveData
    }
}