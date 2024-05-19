package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "pe" attribute in the api
@Parcelize
data class Pe(
    val fi: String,
    val si: String
):Parcelable