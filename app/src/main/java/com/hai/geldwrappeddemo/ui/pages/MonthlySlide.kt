package com.hai.geldwrappeddemo.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hai.geldwrappeddemo.data.DummyData
import com.hai.geldwrappeddemo.data.MonthlyExpenseSummary
import kotlin.math.round

@Composable
fun MonthlySlide(summary: MonthlyExpenseSummary) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = " Your total spend for the month ${summary.monthName} was",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "$${summary.totalSpent}",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Compared with last month's $${summary.totalSpent - 150}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Which is ~ ${round((summary.totalSpent / (summary.totalSpent - 150) - 1) * 100,)}% more",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
        }
    }

}

@Preview
@Composable
private fun MonthlySlidePreview(){

    MonthlySlide(DummyData.monthlySummaries[0])
}

