package com.hai.geldwrappeddemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.hai.geldwrappeddemo.data.DummyData
import com.hai.geldwrappeddemo.ui.pages.FinancialWrappedStories
import com.hai.geldwrappeddemo.ui.theme.GeldWrappedDemoTheme
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeldWrappedDemoTheme {
//               FinancialWrappedStories(
//                   monthlyExpenses = DummyData.monthlySummaries,
//                   yearlySummary = DummyData.yearlySummary
//               )
//                InfiniteColorTransitionDemo()
//                InfiniteMovingGradientDemo()
                InfiniteGradientColorCycle()
            }
        }
    }
}

@Composable
fun InfiniteGradientColorCycle() {
    // A list of gradients, each gradient defined by two colors
    val gradientSets = listOf(
//        listOf(Color.Red, Color.Yellow),
//        listOf(Color.Yellow, Color.Cyan),
//        listOf(Color.Cyan, Color.Blue),
//        listOf(Color.Blue, Color.Magenta)
        listOf(Color(255, 127, 127), Color(250, 218, 94)),
        listOf(Color(250, 218, 94), Color(21, 244, 238)),
        listOf(Color(21, 244, 238), Color(135, 206, 235)),
        listOf(Color(135, 206, 235), Color(207, 113, 175)),
    )

    val infiniteTransition = rememberInfiniteTransition( label = "")

    // Animate a float from 0 to the size of gradientSets
    val animatedValue by infiniteTransition.animateFloat(
        label = "",
        initialValue = 0f,
        targetValue = gradientSets.size.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 11000, easing = LinearEasing),
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
    )
}