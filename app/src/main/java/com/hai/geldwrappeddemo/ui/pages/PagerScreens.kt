package com.hai.geldwrappeddemo.ui.pages

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.hai.geldwrappeddemo.data.MonthlyExpenseSummary
import com.hai.geldwrappeddemo.data.YearlyExpenseSummary
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min


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

    val direction = listOf("left", "right", "top", "bottom")
    val (currentDirection, setCurrentDirection) = remember { mutableStateOf(direction.random()) }

    LaunchedEffect(currentStep.intValue){
        setCurrentDirection(direction.random())
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // A list of gradients, each gradient defined by two colors
        val gradientSets = listOf(
            //  Dark Cyan to Dark Magenta
            listOf(Color(0, 139, 139), Color(139, 0, 139)),
            // Yellow to Florescent Cyan
            listOf(Color(139, 0, 139), Color(204, 41, 54)),
            // Cyan to Light Blue
            listOf( Color(204, 41, 54), Color(34, 108, 224)),
            // Light Blue to Light Magenta
            listOf( Color(34, 108, 224), Color(255, 164,0)),
        )

        val infiniteTransition = rememberInfiniteTransition( label = "")

        // Animate a float from 0 to the size of gradientSets
        val animatedValue: Float by infiniteTransition.animateFloat(
            label = "",
            initialValue = 0f,
            targetValue = gradientSets.size.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 15000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        val currentIndex = floor(animatedValue).toInt() % gradientSets.size
        val nextIndex = (currentIndex + 1) % gradientSets.size
        val fraction = animatedValue - floor(animatedValue)

        // For simplicity, assume each gradient has exactly 2 colors:
        val currentGradient = gradientSets[currentIndex]
        val nextGradient = gradientSets[nextIndex]

        // Interpolate each color stop
        val color1 = lerp(currentGradient[0], nextGradient[0], fraction)
        val color2 = lerp(currentGradient[1], nextGradient[1], fraction)

        // Create the animated gradient brush
        val gradientBrush = Brush.linearGradient(
            colors = listOf(color1, color2),
            start = Offset(0f, 0f),
            end = Offset(1000f, 1000f) // arbitrary width
        )

        // The main clickable area
        val contentModifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
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
        // Define transitions based on the randomly chosen direction
        val enterTransition = when (currentDirection) {
            "left" -> slideInHorizontally(
                initialOffsetX = { fullWidth: Int -> -fullWidth },
                animationSpec = tween(700)
            )
            "right" -> slideInHorizontally(
                initialOffsetX = { fullWidth: Int -> fullWidth },
                animationSpec = tween(700)
            )
            "top" -> slideInVertically(
                initialOffsetY = { fullHeight: Int -> -fullHeight },
                animationSpec = tween(700)
            )
            "bottom" -> slideInVertically(
                initialOffsetY = { fullHeight: Int -> fullHeight },
                animationSpec = tween(700)
            )
            else -> slideInHorizontally(
                initialOffsetX = { fullWidth: Int -> -fullWidth },
                animationSpec = tween(700)
            )
        }

        val exitTransition = when (currentDirection) {
            "left" -> slideOutHorizontally(
                targetOffsetX = { fullWidth: Int -> fullWidth },
                animationSpec = tween(700)
            )
            "right" -> slideOutHorizontally(
                targetOffsetX = { fullWidth: Int -> -fullWidth },
                animationSpec = tween(700)
            )
            "top" -> slideOutVertically(
                targetOffsetY = { fullHeight: Int -> fullHeight },
                animationSpec = tween(700)
            )
            "bottom" -> slideOutVertically(
                targetOffsetY = { fullHeight: Int -> -fullHeight },
                animationSpec = tween(700)
            )
            else -> slideOutHorizontally(
                targetOffsetX = { fullWidth: Int -> fullWidth },
                animationSpec = tween(700)
            )
        }

        AnimatedContent(
            targetState = currentStep.intValue,
            transitionSpec = {enterTransition.togetherWith(exitTransition)},
            modifier = contentModifier, label = "WrappedSlideAnimation"
        ) { step ->
            slides[step].invoke()
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
                        durationMillis = ((4f - progress.value) * stepDuration).toInt(),
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
