package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "at" attribute in "sc" in the api
@Parcelize
data class AtX(
    val r: Int,
    val c: Int,
    val ht: Int
):Parcelable