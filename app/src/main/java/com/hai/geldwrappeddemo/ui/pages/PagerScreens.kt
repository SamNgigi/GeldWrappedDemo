package com.hai.geldwrappeddemo.ui.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hai.geldwrappeddemo.data.MonthlyExpenseSummary
import com.hai.geldwrappeddemo.data.YearlyExpenseSummary

data class WrappedPage(
    val title: String,
    val Content: @Composable () -> Unit
)

@Composable
fun FinancialWrappedPager(
    monthlyExpenses: List<MonthlyExpenseSummary>,
    yearlySummary: YearlyExpenseSummary
){
   val pages = buildWrappedPages(monthlyExpenses, yearlySummary)
    val pagerState = rememberPagerState(){
        pages.size
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ){pageIndex ->
        pages[pageIndex].Content()

    }
}

private fun buildWrappedPages(
    monthlyExpenses: List<MonthlyExpenseSummary>,
    yearlySummary: YearlyExpenseSummary
): List<WrappedPage>{
    val pages = mutableListOf<WrappedPage>()

    pages.add(WrappedPage("Intro") { IntroSlide() })
    monthlyExpenses.forEach {summary ->
        pages.add(WrappedPage("Month: ${summary.monthName}"){
            MonthlySlide(summary = summary)
        })
    }
    pages.add(WrappedPage("Yearly Summary"){ YearlySlide(summary = yearlySummary) })
    pages.add(WrappedPage("Thank You"){ ThankYouSlide()})

    return pages
}