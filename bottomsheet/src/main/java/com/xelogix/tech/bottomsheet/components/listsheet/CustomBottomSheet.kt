package com.xelogix.tech.bottomsheet.components.listsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xelogix.tech.bottomsheet.data.DataHelper
import kotlinx.coroutines.launch

/**
 * A composable function to display a custom bottom sheet with a list of items.
 *
 * @param items: The list of `T` type items to be displayed in the bottom sheet.
 * @param itemContent: A composable function that defines how each item should be displayed.
 *        It takes a single argument of type `T`.
 * @param isItemMatchingSearch: An optional lambda function that determines whether an item matches
 *        the search query. It takes two arguments: the item itself and the search query (String).
 *        If not provided, search functionality is disabled.
 * @param onItemClick: A callback function that is called when an item is clicked. It takes two arguments:
 *        the index of the clicked item (Int) and the item itself (T).
 * @param onSheetClose: A callback function that is called when the bottom sheet is closed.
 * @param title: The title of the bottom sheet (String).
 * @param description: An optional description text for the bottom sheet (String).
 * @param closeIcon: An optional ImageVector resource to display a close icon in the bottom sheet.
 * @param sheetState: The state of the bottom sheet. Defaults to a new `rememberModalBottomSheetState()`.
 * @param containerColor: Color of Sheet Background.
 * @param dragHandle - Optional visual marker to swipe the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CustomBottomSheet(
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    isItemMatchingSearch: ((T, String) -> Boolean)? = null,
    onItemClick:(Int, T) -> Unit={ _, _ ->},
    onSheetClose: () -> Unit={},
    title: String?=null,
    description: String? = null,
    closeIcon: ImageVector? = Icons.Default.Close,
    sheetState: SheetState = rememberModalBottomSheetState(),
    containerColor:Color=Color.White,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
) {
    val coroutineScope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf("") }
    var filteredItems = items
    isItemMatchingSearch?.let { itemMatchingSearch ->
        filteredItems = items.filter { itemMatchingSearch(it, searchText) }
    }
    ModalBottomSheet(
        sheetState = sheetState,
        containerColor=containerColor,
        dragHandle=dragHandle,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
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
                    if (closeIcon != null) {
                        //we will replace it with our Generic Compose
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
                if (description != null) {
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                // Search bar
                isItemMatchingSearch?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomSearchField(onSearch = {
                        searchText = it
                    })
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Item list
                LazyColumn {
                    items(filteredItems.size) { index ->
                        val item = filteredItems[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    onItemClick(index, item)
                                    coroutineScope.launch { sheetState.hide() }
                                }
                        ) {
                            itemContent(item)
                        }
                    }
                }
            }
        },
        windowInsets = WindowInsets.ime,
        onDismissRequest = {
            onSheetClose()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "CustomSearchField")
@Composable
private fun PreviewCustomSearchField() {
    CustomBottomSheet(
        items = DataHelper.getList(),
        itemContent = { item -> SheetItem(title = item.title, leftIcon = Icons.Filled.AccountBox) },
        isItemMatchingSearch = { item, query -> item.title.contains(query, ignoreCase = true) },
        title = "Countries List",
        sheetState = SheetState(initialValue= SheetValue.Expanded,skipPartiallyExpanded = true),
    )
}




