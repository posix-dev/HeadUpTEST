package com.example.headuptest.summary

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.headuptest.ui.theme.White

class SummaryScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Summary",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                SummaryItem("Total Intake")
                SummaryItem("Carbs")
                SummaryItem("Fat")
                SummaryItem("Protein")
            }
        }
    }

    @Composable
    private fun SummaryItem(title: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomProgress()
            Column(
                modifier = Modifier.padding(start = 36.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = title,
                    color = White,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    modifier = Modifier,
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    @Composable
    private fun CustomProgress() {
        DualArcProgressBar(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(96.dp),
            progressPercent = 0.9f
        )
    }

    @Composable
    fun DualArcProgressBar(
        modifier: Modifier = Modifier,
        strokeWidth: Dp = 12.dp,
        progressPercent: Float = 0.9f,
        gapPercent: Float = 0.15f,
        primaryColor: Color = Color(0xFFCEB9FF),
        secondaryColor: Color = Color(0xFF423C4F),
        minSecondaryAngle: Float = 2f,
    ) {
        val isFullFilled = progressPercent >= 1f
        val totalAngle = 360f

        val safeProgress = progressPercent.coerceIn(0f, 1f)
        val safeGap = gapPercent.coerceIn(0f, 1f)

        val rawRemaining = (1f - safeProgress - safeGap).coerceAtLeast(0f)

        val fillable = safeProgress + rawRemaining
        val scale = if (fillable > 0f) (1f - safeGap) / fillable else 0f

        var progressAngle = totalAngle * safeProgress * scale
        var remainingAngle = totalAngle * rawRemaining * scale
        val gapAngle = totalAngle * safeGap

        if (remainingAngle < minSecondaryAngle) {
            val delta = minSecondaryAngle - remainingAngle
            remainingAngle = minSecondaryAngle
            progressAngle = (progressAngle - delta).coerceAtLeast(0f)
        }

        Canvas(modifier = modifier) {
            val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)

            drawArc(
                color = primaryColor,
                startAngle = -305f - progressAngle / 2f,
                sweepAngle = if (progressPercent == 1f) totalAngle else progressAngle,
                useCenter = false,
                style = stroke
            )

            if (isFullFilled) {
                return@Canvas
            }

            drawArc(
                color = secondaryColor,
                startAngle = 235f - remainingAngle / 2f,
                sweepAngle = if (progressAngle == 0f) totalAngle else remainingAngle,
                useCenter = false,
                style = stroke
            )
        }
    }


}