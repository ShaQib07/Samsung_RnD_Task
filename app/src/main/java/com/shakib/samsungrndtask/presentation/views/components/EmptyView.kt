package com.shakib.samsungrndtask.presentation.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shakib.samsungrndtask.R
import com.shakib.samsungrndtask.presentation.theme.SamsungRDTaskTheme

// Use this view whenever you don't have any data
@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(R.string.no_item))
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    SamsungRDTaskTheme {
        EmptyView(modifier = Modifier.fillMaxSize())
    }
}
