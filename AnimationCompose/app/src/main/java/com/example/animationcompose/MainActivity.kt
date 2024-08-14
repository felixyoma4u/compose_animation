package com.example.animationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animationcompose.ui.theme.AnimationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationComposeTheme {

                Column(
                    modifier =
                    Modifier.fillMaxSize()
                ) {
                    AnimateStateExample()
                }
            }
        }
    }
}

@Composable
fun AnimateStateExample() {
    var isClicked by remember {
        mutableStateOf(false)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isClicked) 0.2f else 1f,
        label = "animate",
        animationSpec = tween(
            durationMillis = 500
        )
    )

//    val color = animateColorAsState(
//        targetValue = if (isClicked) Color.Blue else Color.Green,
//        label = "color",
//        animationSpec = tween(durationMillis = 1000)
//    )


    val height = animateDpAsState(
        targetValue = if (isClicked) 300.dp else 150.dp,
        label = "height",
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val targetOffSet = animateIntOffsetAsState(
        targetValue = if (isClicked) IntOffset(
            x = 0,
            y = 200
        ) else IntOffset.Zero
    )

    val infiniteTransiton = rememberInfiniteTransition()

    val color = infiniteTransiton.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )

    val scale = infiniteTransiton.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )


//    Box(
//        modifier = Modifier
//            .layout { measurable, constraints ->
//                val placeable = measurable.measure(constraints)
//                layout(placeable.width+targetOffSet.value.x, placeable.height+targetOffSet.value.y){
//                    placeable.placeRelative(targetOffSet.value)
//                }
//            }
//            .fillMaxWidth()
//            .padding(12.dp)
//            .clickable {
//                isClicked = !isClicked
//            }
//            .height(200.dp)
//            .background(color = Color.Red)
//    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
        .height(200.dp)
        .clickable { isClicked = !isClicked }
        .background(color = Color.Green),
        contentAlignment = Alignment.Center) {
        Text(
            text = "Felix Amimate",
            modifier = Modifier.graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            },
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = color.value
        )
    }
}

