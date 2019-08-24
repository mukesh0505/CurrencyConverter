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
        return currency.removePrefix(sourceCurrency)
    }

    fun displayAmount(): String {
        val dec = DecimalFormat("#,###.##")
        return dec.format(amount * rate / selectedCurrencyValue).toString()
    }
}