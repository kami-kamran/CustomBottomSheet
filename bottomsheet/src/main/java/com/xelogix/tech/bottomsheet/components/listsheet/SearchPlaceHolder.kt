package com.xelogix.tech.bottomsheet.components.listsheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchPlaceHolder(
    modifier: Modifier = Modifier,
    hint:String="Search..."
) {
    Text(text =hint,modifier=modifier, color = Color.Gray)
}

@Preview(name = "SearchPlaceHolder")
@Composable
private fun PreviewSearchPlaceHolder() {
    SearchPlaceHolder()
}