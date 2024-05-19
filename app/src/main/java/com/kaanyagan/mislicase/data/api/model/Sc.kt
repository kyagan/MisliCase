package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "sc" attribute in the api
@Parcelize
data class Sc(
    val st: Int,
    val abbr: String,
    val min: Int,
    val ht: HtX,
    val at: AtX
):Parcelable