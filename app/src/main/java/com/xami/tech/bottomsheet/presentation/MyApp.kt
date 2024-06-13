package com.xami.tech.bottomsheet.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xami.tech.bottomsheet.data.DataHelper
import com.xami.tech.bottomsheet.R
import com.xelogix.tech.bottomsheet.components.listsheet.CustomBottomSheet
import com.xelogix.tech.bottomsheet.components.listsheet.SheetItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var showSheet by remember { mutableStateOf(false) }
    val closeAble by remember { mutableStateOf(false) }
    val sheetState = rememberStandardBottomSheetState(confirmValueChange = { closeAble }, initialValue = SheetValue.Hidden,skipHiddenState=false)
    val coroutineScope = rememberCoroutineScope()
    if (showSheet) {
        CustomBottomSheet(
            items = DataHelper.getList(),
            itemContent = { item -> SheetItem(title = item.title, leftIcon = R.drawable.ic_successfull) },
            isItemMatchingSearch = { item, query -> item.title.contains(query, ignoreCase = true) },
            title = "Countries List",
            sheetState = sheetState
        )
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch { sheetState.show() }
                showSheet = true
            }) {
                Text(text = "Show BottomSheet")
            }
        }
    }
}

@Preview(name = "MyApp")
@Composable
private fun PreviewMyApp() {
    MyApp()
}