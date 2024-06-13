package com.xelogix.tech.bottomsheet.components.alertsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xelogix.tech.bottomsheet.R
import com.xelogix.tech.bottomsheet.components.GenericImage
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertSheet(
    title: String?=null,
    description: String? = null,
    closeIcon: ImageVector? = Icons.Default.Close,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onSheetClose: () -> Unit={},
    img: @Composable (() -> Unit)? ={ GenericImage(image = R.drawable.ic_successfull) },
    buttonPositive: @Composable (() -> Unit)?  = null,
    buttonNegative: @Composable (() -> Unit)? = null,
    buttonLater: @Composable (() -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        sheetState = sheetState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                img?.let { it }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Title we will replace it with our Generic Compose
                    title?.let {
                        Text(
                            text = title,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        )
                    }
                    closeIcon?.let {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    onSheetClose()
                                    sheetState.hide()
                                }

                            },
                        ) {
                            Icon(
                                imageVector = closeIcon,
                                contentDescription = "Close Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                // Description (optional) we will replace it with our Generic Compose
                description?.let {
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // buttons list

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    buttonNegative?.let {it}
                    buttonLater?.let {it}
                    buttonPositive?.let {it}
                }
            }
        },
        windowInsets = WindowInsets.ime,
        onDismissRequest = {
            onSheetClose()
        }
    )
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "AlertSheet")
@Composable
private fun PreviewAlertSheet() {
    AlertSheet(
        title = "Alert",
        description = "This is a test description",
        img = { GenericImage(image = R.drawable.ic_successfull) },
        onSheetClose = {},
        sheetState = SheetState(initialValue = SheetValue.Expanded, skipPartiallyExpanded = true),
    )
}*/
