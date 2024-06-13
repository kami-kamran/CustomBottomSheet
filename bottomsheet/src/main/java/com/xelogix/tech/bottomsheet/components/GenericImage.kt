package com.xelogix.tech.bottomsheet.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xelogix.tech.bottomsheet.R
import com.xelogix.tech.bottomsheet.components.alertsheet.rememberAsyncImagePainterWithPlaceholder

@Composable
fun <T> GenericImage(
    image: T,
    modifier: Modifier = Modifier
        .width(200.dp)
        .height(200.dp)
        .fillMaxWidth()
) {
    when (image) {
        is Int -> Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier,
        )
        is String -> Image(
            painter = rememberAsyncImagePainterWithPlaceholder(
                model = image,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background)
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier

        )
        is Bitmap -> Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Preview(name = "GenericImage")
@Composable
private fun PreviewGenericImage() {
    GenericImage(image = R.drawable.ic_successfull)
}