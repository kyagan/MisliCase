package com.kaanyagan.mislicase.data.api.service

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.Response
import retrofit2.http.GET

interface MatchService {

    @GET("v2/statistics/sport/SOCCER/matches")
    suspend fun getAllMatches(): Response<List<Data>>
}