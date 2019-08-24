package com.mkr.currencyconverter.model

import com.google.gson.annotations.SerializedName

data class CurrencyRateResponse(
        @SerializedName("success") val success: String,
        @SerializedName("terms") val terms: String,
        @SerializedName("privacy") val privacy: String,
        @SerializedName("source") val source: String,
        @SerializedName("timestamp") val timeStamp: Long = 0,
        @SerializedName("quotes") val rates: HashMap<String, Double>
)