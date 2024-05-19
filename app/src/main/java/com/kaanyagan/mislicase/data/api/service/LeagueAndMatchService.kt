package com.kaanyagan.mislicase.data.api.service

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.model.Response
import retrofit2.http.GET

// A method that issues a GET request to retrieve all matches.
// The returned response contains match data of type "Data" in a list.
interface LeagueAndMatchService {

    @GET("v2/statistics/sport/SOCCER/matches")
    suspend fun getAllLeaguesAndMatches(): Response<List<Data>>
}