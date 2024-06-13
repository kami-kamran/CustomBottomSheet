package com.xelogix.tech.bottomsheet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xelogix.tech.bottomsheet.R

@Composable
fun <T> IconCardViewDefault(icon: T, iconWithCardBg:Boolean=false){
    if (iconWithCardBg){
        IconCardView(icon, Color.White, Color.Cyan)
    }else{
        GenericImage(image = icon,modifier = Modifier.size(44.dp))
    }
}
@Composable
fun <T> IconCardView(
    icon: T,
    backgroundColor: Color,
    strokeColor: Color,
    strokeWidth: Dp = 2.dp,
    cornerRadius: Dp = 5.dp,
) {
    Card(
        border = BorderStroke(strokeWidth,strokeColor),
        colors = CardDefaults.cardColors(
            contentColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        GenericImage(image = icon,modifier = Modifier.size(44.dp))
    }
}
@Preview(name = "IconCardViewDefault")
@Composable
private fun PreviewIconCardViewDefault() {
    IconCardViewDefault(icon = R.drawable.ic_successfull)
}