package com.prashant.nogitissues.network

import com.prashant.nogitissues.data.IssueItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface IssueService {

    fun getIssues(page: Int, limit: Int): Single<List<IssueItem>>

    fun getIssueDetails(issueId: Int) : Single<IssueItem>

    class Impl @Inject constructor(private val issuesApi: IssuesApi) : IssueService {

        override fun getIssues(page: Int, limit: Int): Single<List<IssueItem>> {
            return issuesApi.getIssues(page, limit)
                .subscribeOn(Schedulers.io())
        }

        override fun getIssueDetails(issueId: Int): Single<IssueItem> {
            return issuesApi.getIssueDetails(issueId)
                .subscribeOn(Schedulers.io())
        }

    }
}