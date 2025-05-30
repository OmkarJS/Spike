package org.example.project.app.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import org.example.project.app.components.percentOfScreenHeight
import org.jetbrains.compose.resources.painterResource
import spike.composeapp.generated.resources.Res
import spike.composeapp.generated.resources.compose_multiplatform

@Composable
fun ThumbnailView(string: String) {
    val painterResource = asyncPainterResource(string)

    @Composable
    fun ThumbnailImage(painter: Painter) {
        Image(
            painter = painter,
            contentDescription = "Video Thumbnail",
            modifier = Modifier
                .fillMaxWidth()
                .height(percentOfScreenHeight(24))
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }

    Box {
        when (painterResource) {
            is Resource.Loading -> {
                ThumbnailImage(painterResource(Res.drawable.compose_multiplatform))
            }

            is Resource.Success -> {
                ThumbnailImage(painterResource.value)
            }

            is Resource.Failure -> {
                ThumbnailImage(painterResource(Res.drawable.compose_multiplatform))
            }
        }

        /*Small1Text(
            text = duration,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = percentOfScreenWidth(2f),
                    bottom = percentOfScreenHeight(0.6f)
                ),
            fontWeight = FontWeight.Bold
        )*/
    }
}
