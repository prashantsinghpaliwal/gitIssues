package com.prashant.nogitissues.ui.issuelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.prashant.nogitissues.common.Resource
import com.prashant.nogitissues.data.DataSourceFactory
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.data.PagedDataSource
import com.prashant.nogitissues.network.IssueService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class IssuesViewModel @ViewModelInject constructor(issueService: IssueService) :
    ViewModel() {

    private var photoDataSourceFactory: DataSourceFactory? = null
    private var dataSourceMutableLiveData: MutableLiveData<PagedDataSource>? = null
    private var executor: Executor? = null
    private var pagedListLiveData: LiveData<PagedList<IssueItem>>? = null


    init {
        photoDataSourceFactory = DataSourceFactory(issueService)
        dataSourceMutableLiveData = photoDataSourceFactory!!.getMutableLiveData()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()

        executor = Executors.newFixedThreadPool(5)
        pagedListLiveData = LivePagedListBuilder(photoDataSourceFactory!!, config)
            .setFetchExecutor(executor!!)
            .build()



    }

    fun getPagedData(): LiveData<PagedList<IssueItem>>? {
        return pagedListLiveData
    }
}