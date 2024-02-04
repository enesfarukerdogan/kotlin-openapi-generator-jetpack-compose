package com.enesfaruk.openapitutorial.apis

import com.enesfaruk.openapitutorial.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import com.enesfaruk.openapitutorial.models.GetAll200ResponseInner

interface DefaultApi {
    /**
     * get all
     * 
     * Responses:
     *  - 200: A JSON array of posts
     *
     * @return [Call]<[kotlin.collections.List<GetAll200ResponseInner>]>
     */
    @GET("posts")
    fun getAll(): Call<kotlin.collections.List<GetAll200ResponseInner>>

}
