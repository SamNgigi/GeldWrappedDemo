package com.hai.geldwrappeddemo.ui.pages

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import kotlin.math.round

/*
@Composable
fun MonthlySlide(summary: MonthlyExpenseSummary) {
    var text1Visible by remember { mutableStateOf(false) }
    var text2Visible by remember { mutableStateOf(false) }
    var text3Visible by remember { mutableStateOf(false) }
    var text4Visible by remember { mutableStateOf(false) }

    // Animation values for spacing
    val spacing1 by animateDpAsState(
        targetValue = if (text1Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )
    val spacing2 by animateDpAsState(
        targetValue = if (text2Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )
    val spacing3 by animateDpAsState(
        targetValue = if (text3Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )

    LaunchedEffect(Unit) {
        delay(1000)
        text1Visible = true
        delay(2000)
        text2Visible = true
        delay(1000)
        text3Visible = true
        delay(1000)
        text4Visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
//                    .height(MaterialTheme.typography.titleLarge.fontSize.value.dp * 3f)
                    .fillMaxWidth()
            ) {
                this@Column.AnimatedVisibility(
                    visible = text1Visible,
                    enter = slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(2000)
                    ) + fadeIn(tween(2000))
                ) {
                    Text(
                        text = "Your total spend for the month ${summary.monthName} was",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(spacing1))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.displayMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth()
            ) {
                this@Column.AnimatedVisibility(
                    visible = text2Visible,
                    enter = slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(3000)
                    ) + fadeIn(tween(3000))
                ) {
                    Text(
                        text = "$${summary.totalSpent}",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }

            Spacer(Modifier.height(spacing2))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.titleMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth()
            ) {
                this@Column.AnimatedVisibility(
                    visible = text3Visible,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(4000)
                    ) + fadeIn(tween(4000))
                ) {
                    Text(
                        text = "Compared with last month's $${summary.totalSpent - 150}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(Modifier.height(spacing3))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.titleMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth()
            ) {
                this@Column.AnimatedVisibility(
                    visible = text4Visible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(5500)
                    ) + fadeIn(tween(5000))
                ) {
                    val percentageIncrease =
                        round((summary.totalSpent / (summary.totalSpent - 150) - 1) * 100)
                    Text(
                        text = "Which is ~ ${percentageIncrease}% more",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                    )
                }
            }
        }
    }
}
*/


@Composable
fun MonthlySlide(summary: MonthlyExpenseSummary) {
    var text1Visible by remember { mutableStateOf(false) }
    var text2Visible by remember { mutableStateOf(false) }
    var text3Visible by remember { mutableStateOf(false) }
    var text4Visible by remember { mutableStateOf(false) }

    // Animation values for spacing
    val spacing1 by animateDpAsState(
        targetValue = if (text1Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )
    val spacing2 by animateDpAsState(
        targetValue = if (text2Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )
    val spacing3 by animateDpAsState(
        targetValue = if (text3Visible) 16.dp else 0.dp,
        animationSpec = tween(1000), label = ""
    )

    LaunchedEffect(Unit) {
        delay(1000)
        text1Visible = true
        delay(2000)
        text2Visible = true
        delay(1000)
        text3Visible = true
        delay(1000)
        text4Visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.titleLarge.fontSize.value.dp * 3f)
                    .fillMaxWidth()
            ) {
                this@Column.AnimatedVisibility(
                    visible = text1Visible,
                    enter = slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(2000)
                    ) + fadeIn(tween(2000))
                ) {
                    Text(
                        text = "Your total spend for the month ${summary.monthName} was",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Spacer(Modifier.height(spacing1))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.displayMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = text2Visible,
                    enter = slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(3000)
                    ) + fadeIn(tween(3000))
                ) {
                    Text(
                        text = "$${summary.totalSpent}",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }


            }

            Spacer(Modifier.height(spacing2))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.titleMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = text3Visible,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(4000)
                    ) + fadeIn(tween(4000))
                ) {
                    Text(
                        text = "Compared with last month's $${summary.totalSpent - 150}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            }

            Spacer(Modifier.height(spacing3))

            Box(
                modifier = Modifier
                    .height(MaterialTheme.typography.titleMedium.fontSize.value.dp * 1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = text4Visible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(5500)
                    ) + fadeIn(tween(5000))
                ) {

                    val percentageIncrease =
                        round((summary.totalSpent / (summary.totalSpent - 150) - 1) * 100)
                    Text(
                        text = "Which is ~ ${percentageIncrease}% more",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                    )
                }


            }
        }
    }
}

@Preview
@Composable
private fun MonthlySlidePreview() {

    MonthlySlide(DummyData.monthlySummaries[0])
}

