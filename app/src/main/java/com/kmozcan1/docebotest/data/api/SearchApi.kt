package com.kmozcan1.docebotest.data.api

import com.kmozcan1.docebotest.data.apimodel.SearchApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Kadir Mert Özcan on 28-Dec-20.
 */

@JvmSuppressWildcards
interface SearchApi {
    @Headers(
        "Accept: application/vnd.github.v3+json"
    )
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String,
        @Query("sort") sort: String?,
        @Query("order") order: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: String?
    ): Single<SearchApiResponse>
}