package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class To(
    val i: Int,
    val n: String,
    val sn: String,
    val p: Int,
    val flag: String
):Parcelable