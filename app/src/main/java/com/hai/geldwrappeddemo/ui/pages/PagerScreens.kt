package com.hai.geldwrappeddemo.ui.pages

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.hai.geldwrappeddemo.data.MonthlyExpenseSummary
import com.hai.geldwrappeddemo.data.YearlyExpenseSummary
import kotlin.math.max
import kotlin.math.min

data class WrappedPage(
    val title: String,
    val Content: @Composable () -> Unit
)

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FinancialWrappedStories(
    monthlyExpenses: List<MonthlyExpenseSummary>,
    yearlySummary: YearlyExpenseSummary,
    stepDurationMillis: Int = 5000,
    onComplete: () -> Unit = {}
) {
    val slides = remember { buildFinancialWrappedSlides(monthlyExpenses, yearlySummary) }
    val stepCount = slides.size
    val currentStep = remember { mutableIntStateOf(0) }
    val isPaused = remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        // The main clickable area
        val contentModifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        // Tap left half => go backward, right half => go forward
                        currentStep.intValue = if (offset.x < constraints.maxWidth / 2) {
                            max(0, currentStep.intValue - 1)
                        } else {
                            min(stepCount - 1, currentStep.intValue + 1)
                        }
                        isPaused.value = false
                    },
                    onPress = {
                        try {
                            // Press & hold => pause
                            isPaused.value = true
                            awaitRelease()
                        } finally {
                            // On release => resume
                            isPaused.value = false
                        }
                    }
                )
            }

        Crossfade(
            targetState = currentStep.intValue,
            modifier = contentModifier, label = "pagerFade"
        ) { slideIndex ->
            Box(modifier = Modifier.fillMaxSize()){
                slides[slideIndex].invoke()
            }
        }

        // The progress indicators at the top
        StoryProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = stepDurationMillis,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentStep.intValue,
            onStepChanged = { currentStep.intValue = it },
            isPaused = isPaused.value,
            onComplete = onComplete
        )
    }
}

private fun buildFinancialWrappedSlides(
    monthlyExpenses: List<MonthlyExpenseSummary>,
    yearlySummary: YearlyExpenseSummary
): List<@Composable () -> Unit> {
    val slides = mutableListOf<@Composable () -> Unit>()
    slides += { IntroSlide() }
    monthlyExpenses.forEach { summary ->
        slides += { MonthlySlide(summary) }
    }
    slides += { YearlySlide(yearlySummary) }
    slides += { ThankYouSlide() }
    return slides
}

@Composable
private fun StoryProgressIndicator(
    modifier: Modifier = Modifier,
    stepCount: Int,
    stepDuration: Int,
    unSelectedColor: Color,
    selectedColor: Color,
    currentStep: Int,
    onStepChanged: (Int) -> Unit,
    isPaused: Boolean = false,
    onComplete: () -> Unit
){
    val currentStepState = remember(currentStep) { mutableIntStateOf(currentStep) }
    val progress = remember(currentStep){ Animatable(0f) }

    Row(modifier=modifier){
        for(i in 0 until stepCount){
            val stepProgress = when{
                i == currentStepState.intValue -> progress.value
                i > currentStepState.intValue -> 0f
                else -> 1f
            }
            LinearProgressIndicator(
                color = selectedColor,
                trackColor = unSelectedColor,
                progress = {stepProgress},
                drawStopIndicator = {},
                strokeCap = StrokeCap.Round,
                gapSize = (-15).dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
                    .height(2.dp)
            )
        }
    }
    LaunchedEffect(isPaused, currentStep){
        if(isPaused){
            progress.stop()
        } else {
            for (i in currentStep until stepCount){
                progress.animateTo(
                    1f,
                    animationSpec = tween(
                        durationMillis = ((1f - progress.value) * stepDuration).toInt(),
                        easing = LinearEasing
                    )
                )
                if (currentStepState.intValue + 1 <= stepCount - 1){
                    progress.snapTo(0f)
                    currentStepState.value += 1
                    onStepChanged(currentStepState.intValue)
                } else {
                    onComplete()
                }
            }
        }
    }
}
