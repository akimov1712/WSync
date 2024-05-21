package ru.topbun.wsync.data.network.dto.forecast

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("text") val text: String,
    @SerializedName("code") val code: Int
)
