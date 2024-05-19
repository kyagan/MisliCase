package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "to" attribute in the api
@Parcelize
data class To(
    val i: Int,
    val n: String,
    val sn: String,
    val p: Int,
    val flag: String
):Parcelable