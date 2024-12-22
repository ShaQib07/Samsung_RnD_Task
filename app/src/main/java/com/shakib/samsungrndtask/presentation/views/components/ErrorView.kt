package com.shakib.samsungrndtask.presentation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.shakib.samsungrndtask.R
import com.shakib.samsungrndtask.presentation.theme.SamsungRDTaskTheme

// Use this view whenever you encounter an error
@Composable
fun ErrorView(modifier: Modifier = Modifier, message: String) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    SamsungRDTaskTheme {
        ErrorView(
            modifier = Modifier.fillMaxSize(),
            message = stringResource(R.string.something_went_wrong)
        )
    }
}
