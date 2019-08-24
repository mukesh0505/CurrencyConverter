package com.mkr.currencyconverter.viewModel

class CurrencyValueViewModel(
        val currency: String,
        val rate: Double,
        val sourceCurrency: String,
        val amount: Double
) {
    fun displayCurrency(): String {
        return currency.removePrefix(sourceCurrency)
    }

    fun displayAmount(): String {
        return (amount * rate).toString()
    }
}