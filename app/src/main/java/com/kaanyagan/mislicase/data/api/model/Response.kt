package com.kaanyagan.mislicase.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Response<T>(
    val success: Boolean,
    val `data`: @RawValue T
):Parcelable