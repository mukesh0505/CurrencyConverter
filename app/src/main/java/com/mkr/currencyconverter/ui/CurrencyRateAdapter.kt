package com.mkr.currencyconverter.ui

import android.databinding.DataBindingUtil.inflate
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mkr.currencyconverter.R
import com.mkr.currencyconverter.databinding.CurrencyItemviewBinding
import com.mkr.currencyconverter.model.CurrencyRate
import com.mkr.currencyconverter.viewModel.CurrencyValueViewModel

class CurrencyRateAdapter: RecyclerView.Adapter<CurrencyRateAdapter.CurrencyViewHolder>() {

    private lateinit var list: ArrayList<CurrencyRate>
    var amount: Double = 1.0
    var source: String = ""
    var selectedCurrencyValue = 1.0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding: CurrencyItemviewBinding = inflate(inflater, R.layout.currency_itemview, p0, false)
        return CurrencyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return if (::list.isInitialized) list.size else 0
    }

    override fun onBindViewHolder(p0: CurrencyViewHolder, p1: Int) = p0.bind(CurrencyValueViewModel(list[p1].currency, list[p1].rate, source, amount, selectedCurrencyValue))

    fun updateList(list: ArrayList<CurrencyRate>) {
        this.list = list
    }

    class CurrencyViewHolder(private val currencyItemviewBinding: CurrencyItemviewBinding): RecyclerView.ViewHolder(currencyItemviewBinding.root) {

        fun bind(currencyValueViewModel: CurrencyValueViewModel) {
            currencyItemviewBinding.currencyRate = currencyValueViewModel
        }
    }
}