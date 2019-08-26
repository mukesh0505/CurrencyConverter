package com.mkr.currencyconverter.helper

import com.mkr.currencyconverter.model.CurrencyListResponse
import com.mkr.currencyconverter.model.CurrencyRateResponse
import com.mkr.currencyconverter.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

    @GET("list?access_key=${Constants.ACCESS_KEY}")
    fun getSupportedCurrencies(): Observable<CurrencyListResponse>

    @GET("live?access_key=${Constants.ACCESS_KEY}")
    fun getCurrencyRates(): Observable<CurrencyRateResponse>
}