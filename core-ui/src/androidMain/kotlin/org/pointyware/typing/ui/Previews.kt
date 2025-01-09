package org.pointyware.typing.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Displays previews in light mode and dark mode.
 */
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class LightDarkModePreview

/**
 * Displays previews in light mode and dark mode.
 */
@Preview(
    name = "Small Phone - Light",
    group = "small-device",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 250,
    heightDp = 400
)
@Preview(
    name = "Small Phone - Dark",
    group = "small-device",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 250,
    heightDp = 400
)
@Preview(
    name = "Galaxy Z Fold 3 - Light",
    group = "tall-device",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 906,
    heightDp = 1104
)
@Preview(
    name = "Galaxy Z Fold 3 - Dark",
    group = "tall-device",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 906,
    heightDp = 1104
)
@Preview(
    name = "Desktop - Light",
    group = "large-device",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 1920,
    heightDp = 1080
)
@Preview(
    name = "Desktop - Dark",
    group = "large-device",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 1920,
    heightDp = 1080
)
annotation class TestDevicePreviews
