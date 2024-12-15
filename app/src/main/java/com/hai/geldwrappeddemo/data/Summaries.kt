package com.hai.geldwrappeddemo.data

import androidx.compose.ui.graphics.Color

data class MonthlyExpenseSummary(
    val monthName: String,
    val totalSpent: Double,
    val topCategory: String,
    val topCategorySpend: Double,
    val dayWithHighestSpend: String,
    val highestSpendAmount: Double,
    val categoryBreakdown: List<CategoryBreakdown>
)
data class CategoryBreakdown(
    val categoryName: String,
    val spent: Double,
    val color: Color
)
data class YearlyExpenseSummary(
   val year: Int,
   val totalSpent: Double,
   val topCategory: String,
   val topCategorySpend: Double,
)

