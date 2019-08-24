package com.mkr.currencyconverter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import com.mkr.currencyconverter.databinding.ActivityMainBinding
import com.mkr.currencyconverter.viewModel.CurrencyRateViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, TextWatcher {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var currencyRateViewModel: CurrencyRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        currencyRateViewModel = CurrencyRateViewModel(this, currencySpinner)
        activityMainBinding.currencyRateViewModel = currencyRateViewModel
        activityMainBinding.currencyRate.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        currencySpinner.onItemSelectedListener = this
        amount.addTextChangedListener(this)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        currencyRateViewModel.updateSourceCurrency(p2)
    }

    override fun afterTextChanged(p0: Editable?) {
        val text = p0?.toString()
        text?.let {
            if (it.isNotEmpty()) {
                currencyRateViewModel.updateAmount(it.toDouble())
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}
