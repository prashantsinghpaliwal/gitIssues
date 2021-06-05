package com.prashant.nogitissues.ui.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prashant.nogitissues.data.IssueItem
import com.prashant.nogitissues.network.IssueService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class IssueDetailViewModel @ViewModelInject constructor(private val issueService: IssueService) :
    ViewModel() {

    private val _issueLiveData = MutableLiveData<IssueItem>()
    val issueLiveData: LiveData<IssueItem>
        get() = _issueLiveData

    private val _messageData = MutableLiveData<String>()
    val messageLiveData: LiveData<String>
        get() = _messageData


    fun issueDetails(issueId: Int) {
        issueService.getIssueDetails(issueId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    _issueLiveData.postValue(it)
                }
            }, {
                _messageData.postValue(it.localizedMessage)
                Log.v("PagingLogs", "message ${it.localizedMessage}")
            })
    }
}