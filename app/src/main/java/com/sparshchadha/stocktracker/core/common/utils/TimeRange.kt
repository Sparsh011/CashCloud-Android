package com.sparshchadha.stocktracker.core.common.utils

enum class TimeRange (val dateStr: String) {
    DAY_1("1D"),
    WEEK_1(("1W")),
    MONTH_1(("1M")),
    YEAR_1(("1Y")),
    YEAR_3(("3Y")),
    ALL(("All"))
}