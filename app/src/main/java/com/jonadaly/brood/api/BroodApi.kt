package com.jonadaly.brood.api

import com.jonadaly.brood.db.entities.Brood
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BroodApi {

    @GET("/v1/brood/")
    fun getBroodById(@Path("brood_uuid") brood_uuid: String): Observable<Brood>

}