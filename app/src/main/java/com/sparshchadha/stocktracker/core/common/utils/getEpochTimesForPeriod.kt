package com.sparshchadha.stocktracker.core.common.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

object EpochTimeHelper {
    fun getEpochTimesForPeriod(
        exchange: String,
        period: String,
        ipoTimestamp: Long? = null
    ): Pair<Long, Long> {
        val tradingHours = getTradingHoursForExchange(exchange)
        val currentTime = ZonedDateTime.now(ZoneId.of(tradingHours.timezone))

        val startTime = when (period.uppercase()) {
            "1D" -> currentTime.minusDays(1)
            "1W" -> currentTime.minusWeeks(1)
            "1M" -> currentTime.minusMonths(1)
            "1Y" -> currentTime.minusYears(1)
            "2Y" -> currentTime.minusYears(2)
            "5Y" -> currentTime.minusYears(5)
            "ALL" -> {
                if (ipoTimestamp != null) {
                    // Use the IPO timestamp if provided
                    ZonedDateTime.ofInstant(
                        Instant.ofEpochSecond(ipoTimestamp),
                        ZoneId.of(tradingHours.timezone)
                    )
                } else {
                    // Default to a very early date
                    ZonedDateTime.of(
                        LocalDate.of(1900, 1, 1),
                        LocalTime.MIDNIGHT,
                        ZoneId.of(tradingHours.timezone)
                    )
                }
            }

            else -> throw IllegalArgumentException("Invalid period specified.")
        }

        val period1 = ZonedDateTime.of(
            startTime.toLocalDate(),
            tradingHours.start,
            ZoneId.of(tradingHours.timezone)
        ).toEpochSecond()

        val period2 = ZonedDateTime.of(
            currentTime.toLocalDate(),
            tradingHours.end,
            ZoneId.of(tradingHours.timezone)
        ).toEpochSecond()

        return Pair(period1, period2)
    }

    private fun getTradingHoursForExchange(exchange: String): TradingHours {
        return when (exchange.uppercase()) {
            "NYSE", "NASDAQ" -> TradingHours(
                start = LocalTime.of(9, 30),
                end = LocalTime.of(16, 0),
                timezone = "America/New_York"
            )

            "LSE" -> TradingHours(
                start = LocalTime.of(8, 0),
                end = LocalTime.of(16, 30),
                timezone = "Europe/London"
            )

            "JPX" -> TradingHours(
                start = LocalTime.of(9, 0),
                end = LocalTime.of(15, 0),
                timezone = "Asia/Tokyo"
            )

            "NSE", "BSE" -> TradingHours(
                start = LocalTime.of(9, 15),
                end = LocalTime.of(15, 30),
                timezone = "Asia/Kolkata"
            )

            else -> TradingHours(
                start = LocalTime.of(0, 0),
                end = LocalTime.of(0, 0),
                "Asia/Kolkata"
            )
        }
    }

    // Data class to hold trading hours information
    private data class TradingHours(
        val start: LocalTime,
        val end: LocalTime,
        val timezone: String
    )

    fun subtractTimeRangeFromEpoch(timeRange: TimeRange): Long {
        val date =
            when (timeRange) {
                TimeRange.DAY_1 -> {
                    LocalDate.now().minusDays(1)
                }

                TimeRange.WEEK_1 -> {
                    LocalDate.now().minusWeeks(1)
                }

                TimeRange.MONTH_1 -> {
                    LocalDate.now().minusMonths(1)
                }

                TimeRange.YEAR_1 -> {
                    LocalDate.now().minusYears(1)
                }

                TimeRange.YEAR_2 -> {
                    LocalDate.now().minusYears(2)
                }

                TimeRange.YEAR_5 -> {
                    LocalDate.now().minusYears(5)
                }

                TimeRange.ALL -> {
                    // Should never execute this, but if it does, throw overflow error
                    LocalDate.now().minusYears(LocalDate.MAX.year.toLong())
                }
            }
        val zoneId = ZoneId.systemDefault()
        val newEpoch = date.atStartOfDay(zoneId).toEpochSecond()
        return newEpoch
    }
}
