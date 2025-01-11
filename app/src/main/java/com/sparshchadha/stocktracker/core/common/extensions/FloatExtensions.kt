package com.sparshchadha.stocktracker.core.common.extensions

fun Float.toCurrencyString(currency: String): String {
    return "${currency.mapCurrencyToSymbol()}%.1f".format(this)
}
