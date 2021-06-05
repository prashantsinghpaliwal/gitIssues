package com.prashant.nogitissues.network


import com.prashant.nogitissues.data.IssueItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IssuesApi {

    @GET("repositories/8514/issues")
    fun getIssues(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Single<List<IssueItem>>


    @GET("repos/rails/rails/issues/{issueId}")
    fun getIssueDetails(
        @Path("issueId") issueId: Int
    ): Single<IssueItem>

}