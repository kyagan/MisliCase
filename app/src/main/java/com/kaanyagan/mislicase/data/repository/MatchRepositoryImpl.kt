package com.kaanyagan.mislicase.data.repository

import com.kaanyagan.mislicase.data.api.model.Data
import com.kaanyagan.mislicase.data.api.service.MatchService
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchService: MatchService
):MatchRepository {
    override suspend fun getAllMatches(): List<Data> {
        return matchService.getAllMatches().data.filter { it.sc.st==5 }.sortedBy { it.to.sn }.sortedBy { it.d }
    }
}