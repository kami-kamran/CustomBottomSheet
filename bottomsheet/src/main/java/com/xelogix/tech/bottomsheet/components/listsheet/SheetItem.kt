package com.xelogix.tech.bottomsheet.components.listsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xelogix.tech.bottomsheet.components.IconCardViewDefault

/**
 * A composable function that defines how each item should be displayed.
 *
 * @param title: The title text for the each item (String).
 * @param description: An optional description text for the each item (String).
 * @param leftIcon: An optional ImageVector resource to display left icon (ImageVector).
 * @param rightIcon: An optional ImageVector resource to display right icon (ImageVector).
 */
@Composable
fun <T> SheetItem(
    title: String,
    description: String? = null,
    leftIcon: T? = null,
    rightIcon: T? = null,
    iconWithCardBg:Boolean=false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left icon (optional)
            leftIcon.let { IconCardViewDefault(it,iconWithCardBg) }
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            // Title (mandatory)
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            // Description (optional)
            if (description != null) {
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
        // Right icon (optional)
        rightIcon?.let { IconCardViewDefault(it,iconWithCardBg) }
    }
}

@Preview(name = "SheetItem")
@Composable
private fun PreviewSheetItem() {
    SheetItem(title = "Pakistan", leftIcon =null)
}