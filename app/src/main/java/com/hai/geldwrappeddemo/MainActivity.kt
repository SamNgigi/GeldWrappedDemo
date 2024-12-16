package com.hai.geldwrappeddemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hai.geldwrappeddemo.data.DummyData
import com.hai.geldwrappeddemo.ui.pages.FinancialWrappedPager
import com.hai.geldwrappeddemo.ui.theme.GeldWrappedDemoTheme
import kotlin.math.max
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeldWrappedDemoTheme {
               Surface(color = MaterialTheme.colorScheme.background){
                   Story()
               }
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun Story(){
   val images = remember {
       listOf(
           R.drawable.image_1,
           R.drawable.image_2,
           R.drawable.image_3,
           R.drawable.image_4,
           R.drawable.image_5,
       )
   }
    val stepCount = images.size
    val currentStep = remember { mutableIntStateOf(0) }
    val isPaused = remember {mutableStateOf(false)}

    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        val imageModifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){
                detectTapGestures(
                    onTap = { offset ->
                        currentStep.intValue = if (offset.x < constraints.maxWidth / 2) {
                            max(0, currentStep.intValue - 1)
                        } else {
                            min(stepCount - 1, currentStep.intValue + 1)
                        }
                        isPaused.value = false
                    },
                    onPress = {
                        try {
                            isPaused.value = true
                            awaitRelease()
                        }finally {
                            isPaused.value = false
                        }
                    }
                )
            }
        Image(
            painter = painterResource(id = images[currentStep.intValue]),
            contentDescription = "Story Image",
            contentScale = ContentScale.FillHeight,
            modifier = imageModifier
        )
        StoryProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = 2_000,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentStep.intValue,
            onStepChanged = {currentStep.intValue = it},
            isPaused = isPaused.value,
            onComplete = {}
        )
    }
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




