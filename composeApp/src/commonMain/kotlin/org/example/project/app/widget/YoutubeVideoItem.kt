package org.example.project.app.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import org.example.project.data.model.Snippet
import org.example.project.data.model.VideoId
import org.example.project.presentation.components.ThemeColors
import org.jetbrains.compose.resources.painterResource
import spike.composeapp.generated.resources.Res
import spike.composeapp.generated.resources.compose_multiplatform

@Composable
fun YoutubeVideoItem (
    videoID: VideoId,
    videoInfo: Snippet,
    themeColors: ThemeColors
) {
    val bestThumbnailUrl = videoInfo.thumbnails.standard?.url
        ?: videoInfo.thumbnails.high?.url
        ?: videoInfo.thumbnails.medium?.url
        ?: videoInfo.thumbnails.default?.url

    bestThumbnailUrl?.let {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            val painterResource = asyncPainterResource(it)

            when (painterResource) {
                is Resource.Loading -> {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                is Resource.Success -> {
                    Image(
                        painter = painterResource.value,
                        contentDescription = "Thumbnail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                is Resource.Failure -> {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = "Video Thumbnail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

           /* Image(
                painter = painter,
                contentDescription = "Video Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )*/

            /*rememberAsyncImagePainter(
                model = imageUrl,
                contentDescription = "Video Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Use aspect ratio for stable layout
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                // Optional: Add placeholders and error images
                // placeholder = painterResource(id = R.drawable.placeholder_image),
                // error = painterResource(id = R.drawable.error_image)
            )*/

            Spacer(modifier = Modifier.height(4.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = videoInfo.title,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row {
                    Text(
                        text = videoInfo.channelTitle,
                        fontSize = 12.sp,
                        color = themeColors.white
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))

                    Text(
                        text = "• 3:00",
                        fontSize = 10.sp,
                        color = themeColors.white
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
