package org.pointyware.typing.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/**
 * Present the user with a dropdown menu of items to select from.
 */
@Composable
fun ItemSelector(
    prompt: String,
    items: List<String>,
    onItemSelected: (Int) -> Unit,
    selectedItem: Int?,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        Row {
            Button(
                onClick = onAction,
                enabled = selectedItem != null,
                modifier = Modifier.weight(1f)
            ) {
                val text = selectedItem?.let { items.getOrNull(it) } ?: ""
                Text("$prompt: $text")
            }
            Button(
                onClick = { expanded = true },
            ) {
                Text("⎊")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(index)
                        expanded = false
                    },
                    text = { Text(item) }
                )
            }
        }
    }
}
