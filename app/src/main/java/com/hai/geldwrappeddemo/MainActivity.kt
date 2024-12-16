package com.hai.geldwrappeddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hai.geldwrappeddemo.data.DummyData
import com.hai.geldwrappeddemo.ui.pages.FinancialWrappedPager
import com.hai.geldwrappeddemo.ui.theme.GeldWrappedDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeldWrappedDemoTheme {
                FinancialWrappedPager(
                    monthlyExpenses = DummyData.monthlySummaries,
                    yearlySummary = DummyData.yearlySummary
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeldWrappedDemoTheme {
        Greeting("Android")
    }
}