package com.hai.geldwrappeddemo.data

import androidx.compose.ui.graphics.Color

object DummyData {
    val monthlySummaries = listOf(
        MonthlyExpenseSummary(
            monthName = "January",
            totalSpent = 1200.0,
            topCategory = "Food",
            topCategorySpend = 450.0,
            dayWithHighestSpend = "Jan 15",
            highestSpendAmount = 120.0,
            categoryBreakdown = listOf(
                CategoryBreakdown("Food", 450.0, Color.Red),
                CategoryBreakdown("Rent", 600.0, Color.Green),
                CategoryBreakdown("Shopping", 150.0, Color.Magenta)
            )
        ),
        MonthlyExpenseSummary(
            monthName = "February",
            totalSpent = 1400.0,
            topCategory = "Rent",
            topCategorySpend = 600.0,
            dayWithHighestSpend = "Feb 20",
            highestSpendAmount = 200.0,
            categoryBreakdown = listOf(
                CategoryBreakdown("Rent", 600.0, Color.Green),
                CategoryBreakdown("Food", 400.0, Color.Red),
                CategoryBreakdown("Utilities", 200.0, Color.Blue),
                CategoryBreakdown("Misc", 200.0, Color.Yellow)
            )
        )
    )
    val yearlySummary = YearlyExpenseSummary(
        year = 2024,
        totalSpent = 16000.0,
        topCategory = "Rent",
        topCategorySpend = 7200.0
    )
}