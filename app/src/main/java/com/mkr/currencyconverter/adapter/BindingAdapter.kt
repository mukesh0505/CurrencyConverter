package com.mkr.currencyconverter.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.Spinner

@BindingAdapter("adapter")
fun setAdapter(spinner: Spinner, arrayAdapter: ArrayAdapter<String>) {
    spinner.adapter = arrayAdapter
}

@BindingAdapter("adapter")
fun setAdapter(recyclerView: RecyclerView, currencyAdapter: CurrencyRateAdapter) {
    recyclerView.adapter = currencyAdapter
}