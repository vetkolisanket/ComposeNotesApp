package com.example.composenotesapp.ui.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.composenotesapp.R

@Composable
fun OrderSection() {
    Column {
        Row {
            DefaultRadioButton(
                isSelected = false,
                onSelectClick = { /*TODO*/ },
                text = stringResource(R.string.title)
            )
//            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                isSelected = true,
                onSelectClick = { /*TODO*/ },
                text = stringResource(R.string.date)
            )
//            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                isSelected = false,
                onSelectClick = { /*TODO*/ },
                text = stringResource(R.string.color)
            )
        }
        Row {
            DefaultRadioButton(
                isSelected = false,
                onSelectClick = { /*TODO*/ },
                text = stringResource(R.string.ascending)
            )
//            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                isSelected = true,
                onSelectClick = { /*TODO*/ },
                text = stringResource(R.string.descending)
            )
        }
    }
}