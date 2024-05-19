package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

//The model used for the all attributes in the api
@Parcelize
data class Response<T>(
    val success: Boolean,
    val `data`: @RawValue T //@RawValue -> Allows the parameter to be used as a reference within the class
):Parcelable