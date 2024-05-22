package com.example.composenotesapp.ui.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    singleLine: Boolean,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            textStyle = textStyle
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle
            )
        }
    }
}