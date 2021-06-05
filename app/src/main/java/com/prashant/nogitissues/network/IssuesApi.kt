package com.prashant.nogitissues.network


import com.prashant.nogitissues.data.IssueItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IssuesApi {

    @GET("issues")
    fun getIssues(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Single<List<IssueItem>>

}