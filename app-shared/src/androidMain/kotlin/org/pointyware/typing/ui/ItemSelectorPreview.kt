package org.pointyware.typing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/**
 *
 */
@Preview
@Composable
fun ItemSelectorPreview(
) {
    var selectedItem: Int? by remember { mutableStateOf(null) }
    ItemSelector(
        prompt = "Example",
        items = listOf("alpha", "beta", "delta"),
        onItemSelected = { selectedItem = it },
        selectedItem = selectedItem,
        onAction = { selectedItem = null },
    )
}
