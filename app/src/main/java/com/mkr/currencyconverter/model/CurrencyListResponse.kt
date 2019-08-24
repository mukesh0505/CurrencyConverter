package com.mkr.currencyconverter.model

import com.google.gson.annotations.SerializedName

data class CurrencyListResponse(
        @SerializedName("success") val success: String,
        @SerializedName("terms") val terms: String,
        @SerializedName("privacy") val privacy: String,
        @SerializedName("currencies") val countryList: HashMap<String, String>
)