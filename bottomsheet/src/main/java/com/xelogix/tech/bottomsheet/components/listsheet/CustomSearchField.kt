package com.xelogix.tech.bottomsheet.components.listsheet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xelogix.tech.bottomsheet.components.HintIcon

/**
 * A composable function to create a custom search field.
 *
 * @param modifier: Modifier for styling the search field (default: Modifier).
 * @param onSearch: A callback function that is called when the user performs a search
 *        action (e.g., pressing enter on the keyboard).
 * @param hint: The hint text displayed when the field is empty (default: "Search...").
 * @param prefix: An optional composable function to display a leading icon before the search field.
 *        Defaults to a Search icon using Icons.Outlined.Search.
 * @param suffix: An optional composable function to display a trailing icon after the search field.
 *        Defaults to null.
 * @param shape: The shape of the search field (default: RoundedCornerShape(10.dp)).
 * @param placeholder: An optional composable function to display a placeholder within the search field
 *        when it's empty. Defaults to a `SearchPlaceHolder` composable with the provided hint text.
 */
@Composable
fun CustomSearchField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    hint: String = "Search...",
    prefix: @Composable (() -> Unit)? = { HintIcon(Icons.Outlined.Search) },
    suffix: @Composable (() -> Unit)? = null,
    shape: Shape=RoundedCornerShape(10.dp),
    placeholder:@Composable (() -> Unit)? = { SearchPlaceHolder(hint = hint) },
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = modifier) {
        TextField(
            value =  textState,
            onValueChange =  { value ->
                textState = value
                onSearch(value.text)
            },
            prefix = prefix,
            suffix=suffix,
            shape = shape,
            placeholder = placeholder,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = shape),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
        )
    }
}

@Preview(name = "CustomSearchField")
@Composable
private fun PreviewCustomSearchField() {
    CustomSearchField(onSearch = {
        println(it)
    })
}