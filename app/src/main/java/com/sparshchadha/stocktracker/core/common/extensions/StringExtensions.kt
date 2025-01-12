package com.sparshchadha.stocktracker.core.common.extensions

fun String.mapCurrencyToSymbol(): String {
    return when (this.trim().uppercase()) {
        "USD" -> "$"   // US Dollar
        "EUR" -> "€"   // Euro
        "GBP" -> "£"   // British Pound
        "INR" -> "₹"   // Indian Rupee
        "JPY" -> "¥"   // Japanese Yen
        "CNY", "RMB" -> "¥"   // Chinese Yuan (Renminbi)
        "AUD" -> "A$"  // Australian Dollar
        "CAD" -> "C$"  // Canadian Dollar
        "CHF" -> "CHF" // Swiss Franc
        "HKD" -> "HK$" // Hong Kong Dollar
        "SGD" -> "S$"  // Singapore Dollar
        "NZD" -> "NZ$" // New Zealand Dollar
        "KRW" -> "₩"   // South Korean Won
        "ZAR" -> "R"   // South African Rand
        "SEK" -> "kr"  // Swedish Krona
        "NOK" -> "kr"  // Norwegian Krone
        "DKK" -> "kr"  // Danish Krone
        "RUB" -> "₽"   // Russian Ruble
        "MXN" -> "Mex$" // Mexican Peso
        "BRL" -> "R$"  // Brazilian Real
        "AED" -> "د.إ" // UAE Dirham
        "SAR" -> "﷼"   // Saudi Riyal
        "THB" -> "฿"   // Thai Baht
        "IDR" -> "Rp"  // Indonesian Rupiah
        "TRY" -> "₺"   // Turkish Lira
        "MYR" -> "RM"  // Malaysian Ringgit
        "PHP" -> "₱"   // Philippine Peso
        "PLN" -> "zł"  // Polish Zloty
        else -> this    // Return the input string if no match is found
    }
}
