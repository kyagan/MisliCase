package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//The model used for the "img" attribute in the api
@Parcelize
data class Img(
    val id: Int
):Parcelable