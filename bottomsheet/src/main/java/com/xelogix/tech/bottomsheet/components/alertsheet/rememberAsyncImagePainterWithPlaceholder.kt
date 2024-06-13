package com.xelogix.tech.bottomsheet.components.alertsheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter
@Composable
fun rememberAsyncImagePainterWithPlaceholder(
    model: Any,
    placeholder: Painter,
    error: Painter
): Painter {
    return rememberAsyncImagePainter(
        model = model,
        placeholder = placeholder,
        error = error
    )
}