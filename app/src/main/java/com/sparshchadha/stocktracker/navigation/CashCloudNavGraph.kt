package com.sparshchadha.stocktracker.navigation

sealed class CashCloudNavGraph(open val route: String) {
    data object StocksScreen : CashCloudNavGraph("stocks_screen")
    data object MutualFundsScreen : CashCloudNavGraph("mutual_funds_screen")
    data object SearchScreen : CashCloudNavGraph("search_screen")
    data object MainHostScreen : CashCloudNavGraph("home_screen")

    data object StockDetailsScreen : CashCloudNavGraph("stock_details_screen") {
        const val SYMBOL_KEY = "stockSymbol"
        const val EXCHANGE_KEY = "stockExchange"
        private const val BASE_ROUTE = "stock_details_screen"
        override val route = "$BASE_ROUTE/{$SYMBOL_KEY}/{$EXCHANGE_KEY}"

        fun createRoute(symbol: String, exchange: String) = "$BASE_ROUTE/$symbol/$exchange"
    }
}