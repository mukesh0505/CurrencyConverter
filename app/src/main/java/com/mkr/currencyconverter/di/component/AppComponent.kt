package com.mkr.currencyconverter.di.component

import com.mkr.currencyconverter.di.module.RetrofitModule
import com.mkr.currencyconverter.di.scope.ApplicationScope
import com.mkr.currencyconverter.viewModel.CurrencyRateViewModel
import dagger.Component

@Component(modules = [RetrofitModule::class])
@ApplicationScope
interface AppComponent {
    fun inject(viewModel: CurrencyRateViewModel)
}