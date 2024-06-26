package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "ht" attribute in the api
@Parcelize
data class Ht(
    val i: Int,
    val n: String,
    val p: Int,
    val sn: String,
    val rc: Int
):Parcelable