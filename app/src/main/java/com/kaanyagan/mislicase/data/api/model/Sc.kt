package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sc(
    val st: Int,
    val abbr: String,
    val min: Int,
    val ht: HtX,
    val at: AtX
):Parcelable