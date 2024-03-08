package com.jesusdmedinac.tetris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jesusdmedinac.tetris.ui.theme.TetrisTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetrisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TetrisApp()
                }
            }
        }
    }
}

@Composable
fun TetrisApp() {
    var step by remember {
        mutableStateOf(0)
    }
    var maxSteps = 0

    val gameLoop = {
        step++
    }
    Scaffold { paddingValues ->
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Calculate max steps
            maxSteps = (size.height / 64).toInt()
            // Single square
            drawRect(
                color = Color.Green,
                topLeft = Offset(0f, step * 64f),
                size = Size(64f, 64f)
            )
            // Fixed last line if screen is not aligned
            drawRect(
                Color.Black,
                topLeft = Offset(0f, maxSteps * 64f),
                size = size.copy(height = size.height - maxSteps)
            )
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            if (step < maxSteps - 1) {
                step++
            } else {
            }
            delay(50)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TetrisTheme {
        TetrisApp()
    }
}