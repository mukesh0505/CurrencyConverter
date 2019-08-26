package com.mkr.currencyconverter.viewModel

import java.text.DecimalFormat

class CurrencyValueViewModel(
        val currency: String,
        val rate: Double,
        val sourceCurrency: String,
        val amount: Double,
        val selectedCurrencyValue: Double
) {
    fun displayCurrency(): String {
        return currency
    }

    fun displayAmount(): String {
        val dec = DecimalFormat("#,###.##")
        //Since, current API doesn't support source currency change.So, This logic helps in source currency change
        return dec.format(amount * rate / selectedCurrencyValue).toString()
    }
}