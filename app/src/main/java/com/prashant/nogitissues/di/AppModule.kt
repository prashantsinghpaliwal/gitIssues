package com.prashant.nogitissues.di

import com.prashant.nogitissues.BuildConfig.BASE_ENDPOINT
import com.prashant.nogitissues.network.IssueService
import com.prashant.nogitissues.network.IssuesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun profileApi(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): IssuesApi {
        return retrofit
            .baseUrl(BASE_ENDPOINT)
            .client(okHttpClient)
            .build()
            .create(IssuesApi::class.java)
    }

    @Provides
    fun profileService(
        api: IssuesApi
    ): IssueService {
        return IssueService.Impl(
            api
        )
    }

}