package org.pointyware.typing.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 */
val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val TypingTheme.smallGap get() = 4.dp
val TypingTheme.mediumGap get() = 8.dp
val TypingTheme.largeGap get() = 16.dp

val TypingTheme.smallPadding get() = 4.dp
val TypingTheme.mediumPadding get() = 8.dp
val TypingTheme.largePadding get() = 16.dp
