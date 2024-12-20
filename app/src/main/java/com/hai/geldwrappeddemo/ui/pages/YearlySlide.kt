package com.hai.geldwrappeddemo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.hai.geldwrappeddemo.data.YearlyExpenseSummary

@Composable
fun YearlySlide(summary: YearlyExpenseSummary){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = "${summary.year} Financial Recap",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total Spent: ${summary.totalSpent}",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Biggest Category: ${summary.topCategory} " +
                        "(Ksh ${summary.topCategorySpend})",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }

    }
}