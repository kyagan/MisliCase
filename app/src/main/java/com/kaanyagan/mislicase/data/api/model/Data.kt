package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

//The model used for the "data" attribute in the api
@Parcelize
data class Data(
    val i: Int,
    val sgi: Int,
    val d: Long,
    val st: String,
    val bri: Int,
    val ht: @RawValue Ht,
    val at: @RawValue At,
    val sc: @RawValue Sc,
    val to: @RawValue To,
    val v: String,
    val str: Boolean,
    val br: @RawValue Br?,
    val pe: @RawValue Pe?,
    val img: @RawValue Img?
):Parcelable{}