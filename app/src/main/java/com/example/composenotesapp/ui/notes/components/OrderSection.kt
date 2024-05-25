package com.example.composenotesapp.ui.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composenotesapp.R
import com.example.composenotesapp.domain.util.NoteOrder
import com.example.composenotesapp.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    order: NoteOrder,
    onOrderChange: (NoteOrder) -> Unit
) {
    Column {
        Row {
            DefaultRadioButton(
                isSelected = order is NoteOrder.Title,
                onSelectClick = { onOrderChange(NoteOrder.Title(order.orderType)) },
                text = stringResource(R.string.title)
            )
            DefaultRadioButton(
                isSelected = order is NoteOrder.Date,
                onSelectClick = { onOrderChange(NoteOrder.Date(order.orderType)) },
                text = stringResource(R.string.date)
            )
            DefaultRadioButton(
                isSelected = order is NoteOrder.Color,
                onSelectClick = { onOrderChange(NoteOrder.Color(order.orderType)) },
                text = stringResource(R.string.color)
            )
        }
        Row {
            DefaultRadioButton(
                isSelected = order.orderType is OrderType.Ascending,
                onSelectClick = { onOrderChange(order.copy(OrderType.Ascending)) },
                text = stringResource(R.string.ascending)
            )
            DefaultRadioButton(
                isSelected = order.orderType is OrderType.Descending,
                onSelectClick = { onOrderChange(order.copy(OrderType.Descending)) },
                text = stringResource(R.string.descending)
            )
        }
    }
}