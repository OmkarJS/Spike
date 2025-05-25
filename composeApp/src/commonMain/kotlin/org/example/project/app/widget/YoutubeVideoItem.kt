package org.example.project.app.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import org.example.project.app.components.ExtraSmallSpacer
import org.example.project.app.components.SmallSpacer
import org.example.project.app.components.percentOfScreenHeight
import org.example.project.data.model.YoutubeVideoItem
import org.example.project.presentation.theme.LocalAppColors
import org.jetbrains.compose.resources.painterResource
import spike.composeapp.generated.resources.Res
import spike.composeapp.generated.resources.compose_multiplatform

@Composable
fun YoutubeVideoItem(
    videoItem: YoutubeVideoItem,
    onClick: () -> Unit
) {
    val colors = LocalAppColors.current

    val snippet = videoItem.snippet
    val bestThumbnailUrl = snippet.thumbnails.standard?.url
        ?: snippet.thumbnails.high?.url
        ?: snippet.thumbnails.medium?.url
        ?: snippet.thumbnails.default?.url

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

    @Composable
    fun VideoInfoView() {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Medium1Text(
                    text = snippet.title,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold
                )
            }

            Row {
                Small2Text(
                    text = snippet.channelTitle,
                    fontWeight = FontWeight.Normal,
                    textColor = colors.grey
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Composable
    fun MainBody(string: String) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).clickable { onClick() }
        ) {
            ExtraSmallSpacer()

            ThumbnailView(string)

            SmallSpacer()

            VideoInfoView()

            ExtraSmallSpacer()
        }
    }

    bestThumbnailUrl?.let {
        MainBody(it)
    }
}
