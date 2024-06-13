package com.xelogix.tech.bottomsheet.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HintIcon(
    icon: ImageVector,
    tint: Color = Color.Gray,
    modifier: Modifier =  Modifier.size(22.dp)
) {
    Icon(
        imageVector = icon,
        tint =tint,
        contentDescription = "Left Icon",
        modifier = modifier
    )
}

@Preview(name = "SearchIcon")
@Composable
private fun PreviewSearchIcon() {
    HintIcon(Icons.Outlined.Search)
}