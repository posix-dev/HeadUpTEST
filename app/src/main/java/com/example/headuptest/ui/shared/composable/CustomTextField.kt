package com.example.headuptest.ui.shared.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction

@Composable
fun CustomTextField(
    modifier: Modifier,
    label: String,
    supportingText: String,
    @DrawableRes resId: Int,
) {
    val text = remember { mutableStateOf("") }

    TextField(
        modifier = modifier.fillMaxWidth(),
        onValueChange = { text.value = it },
        value = text.value,
        label = {
            Text(
                text = label
            )
        },
        supportingText = {
            Text(
                text = supportingText
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurface,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(resId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}