package com.mkr.currencyconverter.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.mkr.currencyconverter.BR
import com.mkr.currencyconverter.CurrencyApi
import com.mkr.currencyconverter.adapter.CurrencyRateAdapter
import com.mkr.currencyconverter.di.component.DaggerAppComponent
import com.mkr.currencyconverter.model.CurrencyListResponse
import com.mkr.currencyconverter.model.CurrencyRate
import com.mkr.currencyconverter.model.CurrencyRateResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CurrencyRateViewModel(private val context: Context, val spinner: Spinner): BaseObservable(), TextWatcher, AdapterView.OnItemSelectedListener {

    private var sourceCurrency: String = ""
    var currencyRate:HashMap<String, Double> = HashMap()
    var currencyList: Array<String> =  emptyArray()

    @Inject
    lateinit var currencyApi: CurrencyApi

    var currencyAdapter = CurrencyRateAdapter()

    init {
        DaggerAppComponent.builder().build().inject(this)
        fetchSupportedCurrency()
        fetchCurrencyRate()
    }

    var amount: Double = 1.0
    @Bindable get() = field
        set(value) {
            field = value
            currencyAdapter.amount = field
            notifyPropertyChanged(BR.amount)
        }

    private fun fetchSupportedCurrency() {
        currencyApi.getSupportedCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CurrencyListResponse> {
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: CurrencyListResponse) {
                        currencyList = t.countryList.keys.toTypedArray()
                        setUpSpinner()
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.toString())
                    }
                })
    }

    private fun fetchCurrencyRate() {
        currencyApi.getCurrencyRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CurrencyRateResponse> {
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: CurrencyRateResponse) {
                        updateList(t.rates)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.toString())
                    }
                })
    }

    private fun updateList(hashMap: HashMap<String, Double>) {
        val currencyRateList: ArrayList<CurrencyRate> = ArrayList()
        for (key in hashMap.keys) {
            currencyRateList.add(CurrencyRate(key.removePrefix("USD"), hashMap[key]!!))
            currencyRate[key.removePrefix("USD")] = hashMap[key]!!
        }
        currencyAdapter.updateList(currencyRateList)
        currencyAdapter.notifyDataSetChanged()
    }

    fun setUpSpinner() {
        currencyList.sort()
        val spinnerAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, currencyList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }

    private fun updateSourceCurrency(index: Int) {
        sourceCurrency = currencyList[index]
        currencyAdapter.source = sourceCurrency
        currencyAdapter.selectedCurrencyValue = currencyRate[sourceCurrency] ?: 1.0
        Toast.makeText(context, sourceCurrency, Toast.LENGTH_SHORT).show()
        currencyAdapter.notifyDataSetChanged()
    }

    private fun updateAmount(amount: Double) {
        this.amount = amount
        currencyAdapter.amount = amount
        currencyAdapter.notifyDataSetChanged()

    }


    override fun afterTextChanged(p0: Editable?) {
        val text = p0?.toString()
        text?.let {
            if (it.isNotEmpty()) {
                updateAmount(it.toDouble())
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        updateSourceCurrency(p2)
    }

    companion object {
        private val TAG = CurrencyRateViewModel::class.java.simpleName
    }
}