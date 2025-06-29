package com.example.headuptest.parameters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.headuptest.R
import com.example.headuptest.ui.shared.composable.CustomTextField

class ParametersScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Parameters",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall
            )
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                "Height",
                "Your height in cm",
                R.drawable.ic_vice_versa_vertical_24
            )
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                "Weight",
                "Your weight in kg",
                R.drawable.ic_vice_versa_horizontal_24
            )
            CustomTextField(
                Modifier.padding(bottom = 16.dp),
                "Age",
                "Your age",
                R.drawable.ic_person_24
            )
        }
    }
}