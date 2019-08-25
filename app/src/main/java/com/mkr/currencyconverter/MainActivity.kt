package com.mkr.currencyconverter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.mkr.currencyconverter.databinding.ActivityMainBinding
import com.mkr.currencyconverter.viewModel.CurrencyRateViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var currencyRateViewModel: CurrencyRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        currencyRateViewModel = CurrencyRateViewModel(this, currencySpinner)
        activityMainBinding.currencyRateViewModel = currencyRateViewModel
        activityMainBinding.currencyRate.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    }
}
