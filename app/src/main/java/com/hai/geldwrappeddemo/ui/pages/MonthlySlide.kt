package com.hai.geldwrappeddemo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hai.geldwrappeddemo.data.MonthlyExpenseSummary

@Composable
fun MonthlySlide(summary: MonthlyExpenseSummary){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(Color.Magenta, Color.Blue)
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "Monthly Summary: ${summary.monthName}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Text(
            text = "Total Spent: ${summary.totalSpent}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = "Top Category: ${summary.topCategory} (Ksh ${summary.topCategorySpend})",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
        )

        Text(
            text = "Highest Single Day Spend: ${summary.dayWithHighestSpend} " +
                    "(Ksh ${summary.highestSpendAmount})",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}