package com.example.composenotesapp.ui.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun DefaultRadioButton(
    isSelected: Boolean,
    onSelectClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelectClick() },
            modifier = Modifier
                .semantics {
                contentDescription = text
            }
        )
//        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}